package engine.model.state;

import engine.model.automaton.AbstractAutomation;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fukon on 7/12/2017.
 */
public abstract class State {

    private AbstractAutomation machine;
    private String name;
    private boolean isFinal;
    private String output;
    private int transitions;
    protected String[][] transitionMap;

    public State(String name, int transitions, String[][] transitionMap){
        setFinal(false);
        setName(name);
        setTransitions(transitions);
        setTransitionMap(transitionMap);
        setOutput("");
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public abstract List<State> nextStates(String input);
    public abstract List<State> nextStates(char input);

    public boolean isFinal() {
        return isFinal;
    }

    public void setFinal(boolean aFinal) {
        isFinal = aFinal;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public int getTransitions() {
        return transitions;
    }

    public void setTransitions(int transitions) {
        this.transitions = transitions;
    }

    public AbstractAutomation getMachine() {
        return machine;
    }

    public void setMachine(AbstractAutomation machine) {
        this.machine = machine;
    }

    public String[][] getTransitionMap() {
        return transitionMap;
    }

    public void setTransitionMap(String[][] transitionMap) {
        this.transitionMap = transitionMap;
    }
}
