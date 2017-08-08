package engine.scenes;

import engine.drawable.ArrowObject;
import engine.drawable.StackObject;
import engine.drawable.StateObject;
import engine.drawable.TapeObject;
import engine.model.Logic;
import engine.model.automaton.PushDown1StackAutomaton;
import engine.model.state.pushdownautomata.*;
import jGame.model.external.ExternalAction;
import jGame.model.game.GameObject;
import jGame.model.game.GameScene;
import jGame.model.graphics.Camera;
import jGame.model.input.Input;
import jGame.model.timer.SimpleTimer;
import jGame.view.Renderer;

import java.awt.event.KeyEvent;

/**
 * Created by fukon on 7/12/2017.
 */
public class Machine02Scene extends GameScene{

    private static final int GRID = 50;

    private Camera camera;
    private boolean isStart;
    private boolean isAuto;
    private SimpleTimer tmrAuto;

    public Machine02Scene(String name) {
        super(name);

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

        PdaHaltState sH;
        PdaScanLeftState sE;
        PdaScanRightState sA, sC;
        PdaReadState sF, sG;
        PdaWriteState sB, sD;

        sA = new PdaScanRightState("A");
        sB = new PdaWriteState("B");
        sC = new PdaScanRightState("C");
        sD = new PdaWriteState("D");
        sE = new PdaScanLeftState("E");
        sF = new PdaReadState("F");
        sG = new PdaReadState("G");
        sH = new PdaHaltState("H");

        // State name | Input | Type | Next State
        String transitionMap[][] = {
                {sA.getName(),"#",PushDown1StackAutomaton.RIGHT,sB.getName()},
                {sB.getName(),"#",PushDown1StackAutomaton.WRITE,sC.getName()},
                {sC.getName(),"b",PushDown1StackAutomaton.RIGHT,sC.getName()},
                {sC.getName(),"a",PushDown1StackAutomaton.RIGHT,sD.getName()},
                {sD.getName(),"a",PushDown1StackAutomaton.WRITE,sC.getName()},
                {sC.getName(),"#",PushDown1StackAutomaton.RIGHT,sE.getName()},
                {sE.getName(),"b",PushDown1StackAutomaton.LEFT,sF.getName()},
                {sF.getName(),"a",PushDown1StackAutomaton.READ,sE.getName()},
                {sE.getName(),"a",PushDown1StackAutomaton.LEFT,sE.getName()},
                {sE.getName(),"#",PushDown1StackAutomaton.LEFT,sG.getName()},
                {sG.getName(),"#",PushDown1StackAutomaton.READ,sH.getName()},
                {sH.getName(),"#",PushDown1StackAutomaton.HALT,sH.getName()}
        };
        Logic.getInstance().setMachine(new PushDown1StackAutomaton(input, transitionMap));
        Logic.getInstance().getMachine().addState(sA);
        Logic.getInstance().getMachine().addState(sB);
        Logic.getInstance().getMachine().addState(sC);
        Logic.getInstance().getMachine().addState(sD);
        Logic.getInstance().getMachine().addState(sE);
        Logic.getInstance().getMachine().addState(sF);
        Logic.getInstance().getMachine().addState(sG);
        Logic.getInstance().getMachine().addState(sH);
        Logic.getInstance().getMachine().initialState(sA.getName());

        StateObject oA = sA.getStateObject();
        StateObject oB = sB.getStateObject();
        StateObject oC = sC.getStateObject();
        StateObject oD = sD.getStateObject();
        StateObject oE = sE.getStateObject();
        StateObject oF = sF.getStateObject();
        StateObject oG = sG.getStateObject();
        StateObject oH = sH.getStateObject();

        TapeObject oTape = ((PushDown1StackAutomaton)Logic.getInstance().getMachine()).getTape().getTapeObject();
        StackObject oStack = ((PushDown1StackAutomaton)Logic.getInstance().getMachine()).getStack().getStackObject();

        oA.isActive(true);

        //Vector2f posA = new Vector2f(720/2,480/2);
        oA.setInitialPos(3 * GRID,1 * GRID);
        oB.setInitialPos(3 * GRID,3 * GRID);
        oC.setInitialPos(5 * GRID,3 * GRID);
        oD.setInitialPos(7 * GRID,3 * GRID);
        oE.setInitialPos(5 * GRID,5 * GRID);
        oF.setInitialPos(3 * GRID,5 * GRID);
        oG.setInitialPos(7 * GRID,5 * GRID);
        oH.setInitialPos(7 * GRID,7 * GRID);

        oTape.getPosition().setValue(25,45);
        oStack.getPosition().setValue(25,25);

        getInputTimers().put(KeyEvent.VK_R, new SimpleTimer(0.125f));
        getInputTimers().put(KeyEvent.VK_N, new SimpleTimer(0.125f));

        camera.reset();

        getActors().clear();
        getActors().add(oA);
        getActors().add(oB);
        getActors().add(oC);
        getActors().add(oD);
        getActors().add(oE);
        getActors().add(oF);
        getActors().add(oG);
        getActors().add(oH);
        getActors().add(new ArrowObject("",2 * GRID,1 * GRID, ArrowObject.RIGHT));
        getActors().add(new ArrowObject("#",3 * GRID,2 * GRID, ArrowObject.DOWN));
        getActors().add(new ArrowObject("#",4 * GRID,3 * GRID, ArrowObject.RIGHT));
        getActors().add(new ArrowObject("b",5 * GRID,2 * GRID, ArrowObject.SELF_DOWN));
        getActors().add(new ArrowObject("a",6 * GRID,3 * GRID - 5, ArrowObject.RIGHT));
        getActors().add(new ArrowObject("a",6 * GRID,3 * GRID + 5, ArrowObject.LEFT));
        getActors().add(new ArrowObject("#",5 * GRID,4 * GRID, ArrowObject.DOWN));
        getActors().add(new ArrowObject("a",4 * GRID,5 * GRID - 5, ArrowObject.RIGHT));
        getActors().add(new ArrowObject("b",4 * GRID,5 * GRID + 5, ArrowObject.LEFT));
        getActors().add(new ArrowObject("a",5 * GRID,6 * GRID, ArrowObject.SELF_UP));
        getActors().add(new ArrowObject("#",6 * GRID,5 * GRID, ArrowObject.RIGHT));
        getActors().add(new ArrowObject("#",7 * GRID,6 * GRID, ArrowObject.DOWN));
        getActors().add(oTape);
        getActors().add(oStack);

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
            isStart = false;
            Logic.getInstance().getMachine().printState();
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


        if(Logic.getInstance().getMachine().isDone())
            renderer.getRendIn().drawString("Current state is final!", 25,65 + 375);
        if(Logic.getInstance().getMachine().isCrashed())
            renderer.getRendIn().drawString("Machine has crashed!", 25,85 + 375);

    }

    @Override
    public void close() {
        for(GameObject o : getActors())
            o.close();
    }
}
