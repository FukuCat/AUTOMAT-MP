package engine.model.state;

import engine.drawable.StateObject;
import engine.model.automaton.AbstractAutomation;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fukon on 7/12/2017.
 */
public abstract class State {

    private StateObject stateObject = null;

    private AbstractAutomation machine;
    private String name;
    private boolean isFinal;

    public State(String name, boolean isFinal){
        setFinal(isFinal);
        setName(name);
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isFinal() {
        return isFinal;
    }

    public void setFinal(boolean aFinal) {
        isFinal = aFinal;
    }

    public AbstractAutomation getMachine() {
        return machine;
    }

    public void setMachine(AbstractAutomation machine) {
        this.machine = machine;
    }

    public StateObject getStateObject() {
        if(stateObject == null){
            stateObject = new StateObject(name);
            stateObject.setFinal(isFinal);
        }
        return stateObject;
    }

    public void setStateObject(StateObject stateObject) {
        this.stateObject = stateObject;
    }
}
