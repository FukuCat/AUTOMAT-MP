package engine.model.automaton;

import engine.model.state.State;

import java.util.List;

/**
 * Created by fukon on 7/12/2017.
 */
public class MealyMachine extends AbstractAutomation{

    private int index;

    // State name | Input | Output | Next State
    private String[][] transitionMap;
    private boolean isCrashed;

    public MealyMachine(String input, String[][] transitionMap) {
        super(input);
        index = 0;
        this.transitionMap = transitionMap;
    }

    @Override
    public void initialize() {
        isCrashed = false;
    }

    @Override
    public void nextState() {
        if(index < getInput().length() && !isCrashed) {
            boolean isFound = false;
            for (int i = 0; i < transitionMap.length; i++) {
                String curr = getCurrentState().getName();
                if (transitionMap[i][0].equalsIgnoreCase(curr) && transitionMap[i][1].equals(getInput().charAt(index) + "")) {
                    isFound = true;
                    getCurrentState().getStateObject().isActive(false);
                    changeState(transitionMap[i][3]);
                    getCurrentState().getStateObject().isActive(true);
                    appendOutput(transitionMap[i][2].charAt(0));
                    break;
                }
            }
            if (!isFound && !getCurrentState().isFinal())
                isCrashed = true;
            index++;
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
        System.out.println("Input:  "+ input);
        System.out.println("Input index: "+ index);
        System.out.println("Output: "+getOutput());
        if(isCrashed)
            System.out.println("Program has crashed!");
        if(isDone())
            System.out.println("Program has reached final state!");

    }

}
