package engine.model.automaton;

import engine.model.state.State;

import java.util.List;

/**
 * Created by fukon on 7/12/2017.
 */
public class MealyMachine extends AbstractAutomation{

    private int index;

    public MealyMachine(String input, State initialState, List<State> states) {
        super(input, initialState, states);
        index = 0;
    }

    @Override
    public void run() {

        System.out.println("Final state: " + getCurrentState().isFinal());
        System.out.println("State: " + getCurrentState().getName());
        System.out.println("Input: " + getInput().charAt(index));
        List<State> nextStates = getCurrentState().nextStates(getInput().charAt(index));
        index++;
        if (nextStates.size() == 1){
            setCurrentState(nextStates.get(0));
            appendOutput(getCurrentState().getOutput());
        }else if( nextStates.size() > 1) {
            // TODO: non-deterministic
            appendOutput(getCurrentState().getOutput());
        }
        System.out.println("Next State: "+getCurrentState().getName());
        System.out.println("Output: "+getOutput());

    }
}
