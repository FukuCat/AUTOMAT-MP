package engine.model.automaton;

import engine.model.state.State;

import java.util.HashMap;
import java.util.List;

/**
 * Created by fukon on 7/12/2017.
 */
public abstract class AbstractAutomation {

    private String input;
    private State currentState;
    private HashMap<String, State> stateMap;
    private StringBuilder output;

    public AbstractAutomation(String input, State initialState, List<State> states){
        setInput(input);
        stateMap = new HashMap<>();
        setCurrentState(initialState);
        output = new StringBuilder();
        for(State s: states){
            s.setMachine(this);
            stateMap.put(s.getName(), s);
        }
    }

    public State getState(String name){
        return stateMap.get(name);
    }

    public abstract void run();


    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public State getCurrentState() {
        return currentState;
    }

    public void setCurrentState(State currentState) {
        this.currentState = currentState;
    }

    public String getOutput() {
        return output.toString();
    }

    public void setOutput(StringBuilder output) {
        this.output = output;
    }

    public void appendOutput(String text){
        output.append(text);
    }
}
