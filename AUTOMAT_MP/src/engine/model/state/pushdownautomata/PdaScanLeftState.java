package engine.model.state.pushdownautomata;

import engine.drawable.StateObject;
import engine.model.state.State;

public class PdaScanLeftState extends State {
    public PdaScanLeftState(String name) {
        super(name, false);
    }
    @Override
    public StateObject getStateObject() {
        if(stateObject == null){
            stateObject = new StateObject("SL");
            stateObject.setFinal(isFinal);
        }
        return stateObject;
    }
}
