package engine.scenes;

import engine.drawable.ArrowObject;
import engine.drawable.StateObject;
import engine.model.automaton.MealyMachine;
import engine.model.automaton.PushDown1StackAutomaton;
import engine.model.state.mealymachine.MealyState;
import engine.model.state.pushdownautomata.*;
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
public class Machine02Scene extends GameScene{

    private static final int GRID = 50;

    private PushDown1StackAutomaton machine;
    private Camera camera;

    public Machine02Scene(String name) {
        super(name);

        camera = new Camera(500.0f);

        init();
        machine.printState();
    }

    @Override
    public void init() {


        String input = "##aaaabbbb#";

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
                {sB.getName(),"#",PushDown1StackAutomaton.WRITE_1,sC.getName()},
                {sC.getName(),"b",PushDown1StackAutomaton.RIGHT,sC.getName()},
                {sC.getName(),"a",PushDown1StackAutomaton.RIGHT,sD.getName()},
                {sD.getName(),"a",PushDown1StackAutomaton.WRITE_1,sC.getName()},
                {sC.getName(),"#",PushDown1StackAutomaton.RIGHT,sE.getName()},
                {sE.getName(),"b",PushDown1StackAutomaton.LEFT,sF.getName()},
                {sF.getName(),"a",PushDown1StackAutomaton.READ_1,sE.getName()},
                {sE.getName(),"a",PushDown1StackAutomaton.LEFT,sE.getName()},
                {sE.getName(),"#",PushDown1StackAutomaton.LEFT,sG.getName()},
                {sG.getName(),"#",PushDown1StackAutomaton.READ_1,sH.getName()},
                {sH.getName(),"#",PushDown1StackAutomaton.HALT,sH.getName()}
        };
        machine = new PushDown1StackAutomaton(input, transitionMap);
        machine.addState(sA);
        machine.addState(sB);
        machine.addState(sC);
        machine.addState(sD);
        machine.addState(sE);
        machine.addState(sF);
        machine.addState(sG);
        machine.addState(sH);
        machine.initialState(sA.getName());

        StateObject oA = sA.getStateObject();
        StateObject oB = sB.getStateObject();
        StateObject oC = sC.getStateObject();
        StateObject oD = sD.getStateObject();
        StateObject oE = sE.getStateObject();
        StateObject oF = sF.getStateObject();
        StateObject oG = sG.getStateObject();
        StateObject oH = sH.getStateObject();

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
