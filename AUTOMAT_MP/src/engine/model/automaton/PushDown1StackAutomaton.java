package engine.model.automaton;

import engine.model.state.pushdownautomata.Stack;
import engine.model.state.pushdownautomata.Tape;

public class PushDown1StackAutomaton extends AbstractAutomation {

    public static final String READ_1 = "R1";
    public static final String WRITE_1 = "W1";
    public static final String RIGHT = "SR";
    public static final String LEFT = "SL";
    public static final String HALT = "HALT";

    // State name | Input | Type | Next State
    private String[][] transitionMap;
    private boolean isCrashed;

    private Tape tape;
    private Stack stack1;

    public PushDown1StackAutomaton(String input, String[][] transitionMap) {
        super(input);
        tape = new Tape();
        this.transitionMap = transitionMap;
        this.stack1 = new Stack();
        initialize();
    }

    @Override
    public void initialize() {
        isCrashed = false;
        tape.initialize(input);
    }

    @Override
    public void nextState() {
        if(!isDone() && !isCrashed) {
            boolean isFound = false;
            String value;
            for (int i = 0; i < transitionMap.length; i++) {
                String curr = getCurrentState().getName();
                if (transitionMap[i][0].equalsIgnoreCase(curr)) {
                    switch (transitionMap[i][2]) {
                        case READ_1:
                            value = stack1.peek() + "";
                            if (transitionMap[i][1].equals(value)) {
                                isFound = true;
                                stack1.pop();
                                getCurrentState().getStateObject().isActive(false);
                                changeState(transitionMap[i][3]);
                                getCurrentState().getStateObject().isActive(true);
                            }
                            break;
                        case WRITE_1:
                            value = transitionMap[i][1];
                            if (transitionMap[i][1].equals(value)) {
                                isFound = true;
                                stack1.push(value.charAt(0));
                                getCurrentState().getStateObject().isActive(false);
                                changeState(transitionMap[i][3]);
                                getCurrentState().getStateObject().isActive(true);
                            }
                            break;
                        case RIGHT:
                            tape.moveRight();
                            value = tape.read() + "";
                            if (transitionMap[i][1].equals(value)) {
                                isFound = true;
                                getCurrentState().getStateObject().isActive(false);
                                changeState(transitionMap[i][3]);
                                getCurrentState().getStateObject().isActive(true);
                            } else
                                tape.moveLeft();
                            break;
                        case LEFT:
                            tape.moveLeft();
                            value = tape.read() + "";
                            if (transitionMap[i][1].equals(value)) {
                                isFound = true;
                                getCurrentState().getStateObject().isActive(false);
                                changeState(transitionMap[i][3]);
                                getCurrentState().getStateObject().isActive(true);
                            } else
                                tape.moveRight();
                            break;
                        case HALT:
                            break;
                    }
                }
                if (isFound)
                    break;
            }
            if (!isFound && !getCurrentState().isFinal())
                isCrashed = true;
        }
    }

    @Override
    public boolean isDone() {
        return getCurrentState().isFinal();
    }

    @Override
    public boolean isCrashed() {
        return isCrashed;
    }

    @Override
    public void printState() {
        System.out.println("---------------");
        System.out.println("Current State: "+ currentState.getName());
        System.out.println("Tape: "+ tape.getString());
        System.out.println("Tape index: "+ (tape.getOffset() + tape.getIndex()));
        System.out.println("Stack: "+ stack1.getString());
        if(isCrashed)
            System.out.println("Program has crashed!");
        if(isDone())
            System.out.println("Program has reached final state!");

    }
}
