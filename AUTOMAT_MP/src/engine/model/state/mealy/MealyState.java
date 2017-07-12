package engine.model.state.mealy;

import engine.model.automaton.AbstractAutomation;
import engine.model.state.State;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fukon on 7/12/2017.
 */
public class MealyState extends State {

    private static final int INPUT = 0;
    private static final int OUTPUT = 1;
    private static final int NEXT_STATE = 2;

    public MealyState(String name, int transitions, String[][] transitionMap) {
        super(name, transitions, transitionMap);
        //transitionMap = new String[3][transitions];
    }

    @Override
    public List<State> nextStates(char input){
        ArrayList<State> result = new ArrayList<>();
        for(int i = 0; i < getTransitions(); i++){
            if(input == (transitionMap[INPUT][i]).charAt(0)){
                // mealy machine will assign output here
                State s = getMachine().getState(transitionMap[NEXT_STATE][i]);
                if(s != null){
                    s.setOutput(transitionMap[OUTPUT][i]);
                    result.add(s);
                }
            }
        }

        return result;
    }

    @Override
    public List<State> nextStates(String input){ return null; }

}
