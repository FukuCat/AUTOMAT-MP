package engine.model.state.pushdownautomata;

import engine.drawable.StateObject;
import engine.model.state.State;

public class PdaReadState extends State {

    public PdaReadState(String name) {
        super(name, false);
    }



    @Override
    public StateObject getStateObject() {
        if(stateObject == null){
            stateObject = new StateObject("R");
            stateObject.setFinal(isFinal);
        }
        return stateObject;
    }

}
