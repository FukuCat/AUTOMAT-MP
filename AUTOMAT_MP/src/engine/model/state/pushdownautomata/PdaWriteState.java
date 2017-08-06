package engine.model.state.pushdownautomata;

import engine.drawable.StateObject;
import engine.model.state.State;

public class PdaWriteState extends State {

    public PdaWriteState(String name) {
        super(name, false);
    }

    @Override
    public StateObject getStateObject() {
        if(stateObject == null){
            stateObject = new StateObject("W");
            stateObject.setFinal(isFinal);
        }
        return stateObject;
    }
}
