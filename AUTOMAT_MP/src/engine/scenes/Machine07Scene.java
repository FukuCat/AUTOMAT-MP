package engine.scenes;

import engine.drawable.ArrowObject;
import engine.drawable.StateObject;
import engine.model.Logic;
import engine.model.automaton.MealyMachine;
import engine.model.state.mealymachine.MealyState;
import jGame.model.external.ExternalAction;
import jGame.model.game.GameObject;
import jGame.model.game.GameScene;
import jGame.model.graphics.Camera;
import jGame.model.input.Input;
import jGame.model.math.Vector2f;
import jGame.model.timer.SimpleTimer;
import jGame.view.Renderer;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Created by fukon on 7/12/2017.
 */
public class Machine07Scene extends GameScene{

    private static final int GRID = 50;

    private Camera camera;

    private boolean isStart;
    private boolean isAuto;
    private SimpleTimer tmrAuto;
    private Font font;

    public Machine07Scene(String name) {
        super(name);

        font = new Font("courier new", Font.PLAIN, 12);
        camera = new Camera(500.0f);
        getExternalActions().put("MACHINE_01", () -> gameSceneManager.changeScene("MACHINE_01"));
        getExternalActions().put("MACHINE_02", () -> gameSceneManager.changeScene("MACHINE_02"));
        getExternalActions().put("MACHINE_07", () -> gameSceneManager.changeScene("MACHINE_07"));
        getExternalActions().put("PLAY", () -> isAuto = true);
        getExternalActions().put("PAUSE", () -> isAuto = false);
        getExternalActions().put("NEXT", () -> Logic.getInstance().getMachine().nextState());
        getExternalActions().put("RESET", () -> init());
        tmrAuto = new SimpleTimer(0.5f);
    }

    @Override
    public void init() {
        isStart = true;
        isAuto = false;
        String input = Logic.getInstance().getInput();

        MealyState sA = new MealyState("A", true);
        MealyState sB = new MealyState("B", true);


        // State name | Input | Output | Next State
        String transitionMap[][] = {
                {sA.getName(),"a","a",sA.getName()},
                {sA.getName(),"b","a",sB.getName()},
                {sB.getName(),"b","b",sB.getName()},
                {sB.getName(),"a","b",sA.getName()},
        };

        Logic.getInstance().setMachine(new MealyMachine(input, transitionMap));
        Logic.getInstance().getMachine().addState(sA);
        Logic.getInstance().getMachine().addState(sB);
        Logic.getInstance().getMachine().initialState(sA.getName());
        sA.getStateObject().isActive(true);

        StateObject oA = sA.getStateObject();
        StateObject oB = sB.getStateObject();

        //Vector2f posA = new Vector2f(720/2,480/2);
        Vector2f posA = new Vector2f(4 * GRID,4 * GRID);
        Vector2f posB = new Vector2f(6 * GRID,4 * GRID);

        oA.setInitialPos(posA);
        oB.setInitialPos(posB);
        oA.setPosition(posA.getX(), posA.getY());
        oB.setPosition(posB.getX(), posB.getY());

        getInputTimers().put(KeyEvent.VK_R, new SimpleTimer(0.125f));
        getInputTimers().put(KeyEvent.VK_N, new SimpleTimer(0.125f));
        camera.reset();
        getActors().clear();
        getActors().add(oA);
        getActors().add(oB);
        getActors().add(new ArrowObject("",3 * GRID,4 * GRID, ArrowObject.RIGHT));
        getActors().add(new ArrowObject("a/b",5 * GRID,4 * GRID + 5, ArrowObject.LEFT));
        getActors().add(new ArrowObject("b/a",5 * GRID,4 * GRID - 5, ArrowObject.RIGHT));
        getActors().add(new ArrowObject("a/a",4 * GRID,3 * GRID, ArrowObject.SELF_DOWN));
        getActors().add(new ArrowObject("b/b",6 * GRID,3 * GRID, ArrowObject.SELF_DOWN));

    }

    @Override
    public void input(Input input, long deltaTime) {
        //System.out.println(deltaTime(double)/ );
        if(getInputTimers().get(KeyEvent.VK_R).checkTime() && input.getKeyboardKey(KeyEvent.VK_R)){
            init();
        }
        if(getInputTimers().get(KeyEvent.VK_N).checkTime() && input.getKeyboardKey(KeyEvent.VK_N)){
            Logic.getInstance().getMachine().nextState();
            Logic.getInstance().getMachine().printState();
        }

        // Camera
        /*
        if(!(input.getKeyboardKey(KeyEvent.VK_UP) || input.getKeyboardKey(KeyEvent.VK_DOWN)))
            camera.accelDecayY(80.0f, true);
        else {
            if(input.getKeyboardKey(KeyEvent.VK_UP))
                camera.accelerateY(-50.0f);
            if(input.getKeyboardKey(KeyEvent.VK_DOWN))
                camera.accelerateY(50.0f);
        }
        if(!(input.getKeyboardKey(KeyEvent.VK_RIGHT) || input.getKeyboardKey(KeyEvent.VK_LEFT)))
            camera.accelDecayX(80.0f, true);
        else {
            if(input.getKeyboardKey(KeyEvent.VK_RIGHT))
                camera.accelerateX(50.0f);
            if(input.getKeyboardKey(KeyEvent.VK_LEFT))
                camera.accelerateX(-50.0f);
        }
        */

    }

    @Override
    public void logic(long deltaTime) {
        if(isStart){
            Logic.getInstance().getMachine().printState();
            isStart = false;
        }
        camera.logic(deltaTime);
        for(GameObject o : getActors())
            o.logic(deltaTime);

        String sAction = getActionQueue().poll();
        ExternalAction a = null;
        if(sAction != null)
            a = getExternalActions().get(sAction);
        if(a != null)
            a.run();
        if(tmrAuto.checkTime() && isAuto)
            Logic.getInstance().getMachine().nextState();
    }

    @Override
    public void render(Renderer renderer) {

        for(GameObject o : getActors())
            o.render(renderer, camera);

        renderer.getRendIn().setColor(Color.BLACK);
        renderer.getRendIn().setFont(font);
        renderer.getRendIn().drawString("Input:  "+Logic.getInstance().getMachine().getInput(), 25,25);
        renderer.getRendIn().drawString("Output: "+Logic.getInstance().getMachine().getOutput(), 25,45);
        if(Logic.getInstance().getMachine().isDone())
            renderer.getRendIn().drawString("Current state is final!", 25,65);
        if(Logic.getInstance().getMachine().isCrashed())
            renderer.getRendIn().drawString("Machine has crashed!", 25,85);
    }

    @Override
    public void close() {
        for(GameObject o : getActors())
            o.close();
    }
}
