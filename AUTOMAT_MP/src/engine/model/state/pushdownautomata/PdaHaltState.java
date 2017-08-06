package engine.model.state.pushdownautomata;

import engine.drawable.StateObject;
import engine.model.state.State;

public class PdaHaltState extends State {

    public PdaHaltState(String name) {
        super(name, true);
    }

    @Override
    public StateObject getStateObject() {
        if (stateObject == null) {
            stateObject = new StateObject("");
            stateObject.setFinal(isFinal);
        }
        return stateObject;
    }
}