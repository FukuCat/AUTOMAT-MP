package engine.model.automaton;

import engine.drawable.StateObject;
import engine.model.state.State;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by fukon on 7/12/2017.
 */
public abstract class AbstractAutomation {

    private String input;
    private HashMap<String, State> stateMap;
    private State currentState;
    private StringBuilder output;

    public AbstractAutomation(String input){
        setInput(input);
        stateMap = new HashMap<>();
        setOutput(new StringBuilder());
        initialize();
    }

    public State getState(String name){
        return stateMap.get(name);
    }

    public void addState(State state){
        if(!stateMap.containsKey(state.getName())){
            state.setMachine(this);
            stateMap.put(state.getName(), state);
            if(currentState == null)
                changeState(state.getName());
        }
    }
    public boolean changeState(String name){
        if(stateMap.containsKey(name)){
            setCurrentState(stateMap.get(name));
            return true;
        }
        return false;
    }
    public void initialState(String name){changeState(name);}

    public abstract void initialize();
    public abstract void nextState();
    public abstract void prevState();
    public abstract boolean isDone();
    public abstract boolean isCrashed();

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String getOutput() {
        return output.toString();
    }

    public void appendOutput(char c){ output.append(c); }
    public void unappendOutput(){ if(output.length() != 0) output.deleteCharAt(output.length() - 1);}
    public int outputSize(){ return output.length(); }

    public void setOutput(StringBuilder output) {
        this.output = output;
    }

    public State getCurrentState() {
        return currentState;
    }

    public void setCurrentState(State currentState) {
        this.currentState = currentState;
    }
}
