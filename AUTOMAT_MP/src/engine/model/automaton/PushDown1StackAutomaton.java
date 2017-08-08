package engine.model.automaton;

import engine.model.state.pushdownautomata.Stack;
import engine.model.state.pushdownautomata.Tape;

public class PushDown1StackAutomaton extends AbstractAutomation {

    public static final String READ = "R1";
    public static final String WRITE = "W1";
    public static final String RIGHT = "SR";
    public static final String LEFT = "SL";
    public static final String HALT = "HALT";

    // State name | Input | Type | Next State
    private String[][] transitionMap;
    private boolean isCrashed;

    private Tape tape;
    private Stack stack;

    private boolean isStart;

    public PushDown1StackAutomaton(String input, String[][] transitionMap) {
        super(input);
        if(input == null)
            input = "##";
        this.transitionMap = transitionMap;
    }

    @Override
    public void initialize() {
        isStart = true;
        isCrashed = false;
        setStack(new Stack());
        setTape(new Tape());
        getTape().initialize(input);
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
                        case READ:
                            value = getStack().peek() + "";
                            if (transitionMap[i][1].equals(value)) {
                                isFound = true;
                                getStack().pop();
                                getCurrentState().getStateObject().isActive(false);
                                changeState(transitionMap[i][3]);
                                getCurrentState().getStateObject().isActive(true);
                            }
                            break;
                        case WRITE:
                            value = transitionMap[i][1];
                            if (transitionMap[i][1].equals(value)) {
                                isFound = true;
                                getStack().push(value.charAt(0));
                                getCurrentState().getStateObject().isActive(false);
                                changeState(transitionMap[i][3]);
                                getCurrentState().getStateObject().isActive(true);
                            }
                            break;
                        case RIGHT:
                            if(!isStart)
                                getTape().moveRight();
                            value = getTape().read() + "";
                            if (transitionMap[i][1].equals(value)) {
                                isStart = false;
                                isFound = true;
                                getCurrentState().getStateObject().isActive(false);
                                changeState(transitionMap[i][3]);
                                getCurrentState().getStateObject().isActive(true);
                            } else
                                getTape().moveLeft();
                            break;
                        case LEFT:
                            if(!isStart)
                                getTape().moveLeft();
                            value = getTape().read() + "";
                            if (transitionMap[i][1].equals(value)) {
                                isStart = false;
                                isFound = true;
                                getCurrentState().getStateObject().isActive(false);
                                changeState(transitionMap[i][3]);
                                getCurrentState().getStateObject().isActive(true);
                            } else
                                getTape().moveRight();
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
        System.out.print("---------------");
        System.out.print("\nCurrent State: "+ currentState.getName());
        System.out.print("\nTape:  "+ getTape().getString());
        System.out.print("\n       ");
        for(int i = 0; i < (getTape().getOffset() + getTape().getIndex()); i++)
            System.out.print(" ");
        System.out.print("^\n");

        System.out.println("Tape index: "+ (getTape().getOffset() + getTape().getIndex()));
        System.out.println("Stack: "+ getStack().getString());
        if(isCrashed)
            System.out.println("Program has crashed!");
        if(isDone())
            System.out.println("Program has reached final state!");

    }

    public Tape getTape() {
        return tape;
    }

    public void setTape(Tape tape) {
        this.tape = tape;
    }

    public Stack getStack() {
        return stack;
    }

    public void setStack(Stack stack) {
        this.stack = stack;
    }
}
