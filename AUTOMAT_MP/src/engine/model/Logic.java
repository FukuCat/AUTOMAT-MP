package engine.model;

import engine.model.automaton.AbstractAutomation;
import engine.model.automaton.MealyMachine;
import engine.model.automaton.PushDown1StackAutomaton;

public class Logic {
    private static Logic instance = null;
    private AbstractAutomation m;
    private String input;

    public Logic(){}

    public static synchronized Logic getInstance(){return instance == null? (instance = new Logic()):instance;}

    public AbstractAutomation getMachine() {
        return m;
    }

    public void setMachine(AbstractAutomation machine) {
        this.m = machine;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }
}
