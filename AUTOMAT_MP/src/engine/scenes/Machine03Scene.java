package engine.scenes;

import engine.drawable.ArrowObject;
import engine.drawable.StateObject;
import engine.model.automaton.MealyMachine;
import engine.model.state.mealymachine.MealyState;
import jGame.model.game.GameObject;
import jGame.model.game.GameScene;
import jGame.model.graphics.Camera;
import jGame.model.input.Input;
import jGame.model.math.Vector2f;
import jGame.model.timer.SimpleTimer;
import jGame.view.Renderer;

import java.awt.event.KeyEvent;

/**
 * Created by fukon on 7/12/2017.
 */
public class Machine03Scene extends GameScene{

    private static final int GRID = 50;

    private MealyMachine machine;
    private Camera camera;

    public Machine03Scene(String name) {
        super(name);

        camera = new Camera(500.0f);

        init();
    }

    @Override
    public void init() {

        // State name | Input | Output | Next State

        MealyState sA = new MealyState("A", true);
        sA.setFinal(true);

        String transitionMap[][] = {
                {sA.getName(),"1","0",sA.getName()},
                {sA.getName(),"0","1",sA.getName()},
        };

        machine = new MealyMachine("1100011", transitionMap);
        machine.addState(sA);
        machine.initialState(sA.getName());
        sA.getStateObject().isActive(true);

        StateObject oA = sA.getStateObject();

        //Vector2f posA = new Vector2f(720/2,480/2);
        Vector2f posA = new Vector2f(7 * GRID,4 * GRID);

        oA.setInitialPos(posA);
        oA.setPosition(posA.getX(), posA.getY());

        getInputTimers().put(KeyEvent.VK_R, new SimpleTimer(0.125f));
        getInputTimers().put(KeyEvent.VK_N, new SimpleTimer(0.125f));
        camera.reset();
        getActors().clear();
        getActors().add(oA);
        getActors().add(new ArrowObject("",6 * GRID,4 * GRID, ArrowObject.RIGHT));
        getActors().add(new ArrowObject("1/0",7 * GRID,3 * GRID, ArrowObject.SELF_DOWN));
        getActors().add(new ArrowObject("0/1",7 * GRID,5 * GRID, ArrowObject.SELF_UP));

    }

    @Override
    public void input(Input input, long deltaTime) {
        //System.out.println(deltaTime(double)/ );
        if(getInputTimers().get(KeyEvent.VK_R).checkTime() && input.getKeyboardKey(KeyEvent.VK_R)){
            init();
        }
        if(getInputTimers().get(KeyEvent.VK_N).checkTime() && input.getKeyboardKey(KeyEvent.VK_N)){
            machine.nextState();
            machine.printState();
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
        camera.logic(deltaTime);
        for(GameObject o : getActors())
            o.logic(deltaTime);
    }

    @Override
    public void render(Renderer renderer) {

        for(GameObject o : getActors())
            o.render(renderer, camera);

    }

    @Override
    public void close() {
        for(GameObject o : getActors())
            o.close();
    }
}
