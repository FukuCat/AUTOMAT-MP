import engine.MachineProject;
import engine.model.automaton.MealyMachine;
import engine.model.automaton.PushDown1StackAutomaton;
import engine.model.automaton.PushDown2StackAutomaton;
import engine.model.state.mealymachine.MealyState;
import engine.model.state.pushdownautomata.*;

public class Main {

    public static void main(String[] args) {
        new MachineProject("MP Demo", 720, 480, 60).start();
        //consoleMachine1();
        //consoleMachine2();
    }

    public static void consoleMachine2(){

        String input = "##aaaabbbb#";

        PushDown1StackAutomaton machine;

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

        for(int i = 0; i <= 100 && !machine.getCurrentState().isFinal(); i++){
            machine.printState();
            machine.nextState();
        }
        machine.printState();

    }

    public static void consoleMachine1(){

        String input = "1100011";

        MealyMachine machine;
        MealyState sA = new MealyState("A", true);

        String transitionMap[][] = {
                {sA.getName(),"1","0",sA.getName()},
                {sA.getName(),"0","1",sA.getName()}
        };

        machine = new MealyMachine(input, transitionMap);
        machine.addState(sA);
        machine.initialState(sA.getName());

        for(int i = 0; i <= 50 && !machine.getCurrentState().isFinal(); i++){
            machine.printState();
            machine.nextState();
        }
        machine.printState();
    }
}
