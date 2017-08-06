package engine.model.state;

import engine.drawable.StateObject;
import engine.model.automaton.AbstractAutomation;
import engine.model.state.State;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fukon on 7/12/2017.
 */
public class MealyState extends State {

    public MealyState(String name, boolean isFinal) {
        super(name, isFinal);
    }

}
