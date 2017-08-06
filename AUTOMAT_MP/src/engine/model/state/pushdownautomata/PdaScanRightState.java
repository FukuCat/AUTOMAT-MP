package engine.model.state.pushdownautomata;

import engine.drawable.StateObject;
import engine.model.state.State;

public class PdaScanRightState extends State {

    public PdaScanRightState(String name) {
        super(name, false);
    }
    @Override
    public StateObject getStateObject() {
        if(stateObject == null){
            stateObject = new StateObject("SR");
            stateObject.setFinal(isFinal);
        }
        return stateObject;
    }
}
