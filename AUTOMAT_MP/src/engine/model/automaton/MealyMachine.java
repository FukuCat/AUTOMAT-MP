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
        if(input == null)
            this.input = "";
        this.transitionMap = transitionMap;
    }

    @Override
    public void initialize() {
        index = 0;
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
            if (!isFound)
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
        System.out.print("---------------");
        System.out.print("\nCurrent State: "+ currentState.getName());
        System.out.print("\nInput:  "+ input);
        System.out.print("\n        ");
        for(int i = 1; i < index; i++)
            System.out.print(" ");
        System.out.print("^\n");

        System.out.println("Input index: "+ index);
        System.out.println("Output: "+getOutput());
        if(isCrashed)
            System.out.println("Program has crashed!");
        if(isDone())
            System.out.println("Program has reached final state!");

    }

}
