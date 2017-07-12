import engine.MachineProject;
import engine.model.automaton.MealyMachine;
import engine.model.state.State;
import engine.model.state.mealy.MealyState;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        //new MachineProject("Kara Demo", 300, 400, 30).start();

        String[][] transitionMap = new String[3][2];
        transitionMap[0][0] = "1";
        transitionMap[1][0] = "0";
        transitionMap[2][0] = "A";
        transitionMap[0][1] = "0";
        transitionMap[1][1] = "1";
        transitionMap[2][1] = "A";
        MealyState s = new MealyState("A", 2, transitionMap);
        List<State> list = new ArrayList<>();
        list.add(s);
        MealyMachine m = new MealyMachine("1100011", s, list);
        m.run();
        m.run();
        m.run();
        m.run();
        m.run();
        m.run();
        m.run();
    }
}
