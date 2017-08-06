package engine.model.automaton;

public class PushDown2StackAutomaton extends AbstractAutomation {

    private int index;

    // State name | Input | Output | Next State
    private String[][] transitionMap;
    private boolean isCrashed;

    public  PushDown2StackAutomaton(String input, String[][] transitionMap) {
        super(input);
        index = 0;
        this.transitionMap = transitionMap;
    }

    @Override
    public void initialize() {
        isCrashed = false;
    }

    @Override
    public void nextState() {
        if(index < getInput().length()) {
            boolean isFound = false;
            for (int i = 0; i < transitionMap.length; i++) {
                String curr = getCurrentState().getName();
                if (transitionMap[i][0].equalsIgnoreCase(curr) && transitionMap[i][1].equals(getInput().charAt(index) + "")) {
                    isFound = true;
                    getCurrentState().getStateObject().isActive(false);
                    changeState(transitionMap[i][3]);
                    getCurrentState().getStateObject().isActive(true);
                    appendOutput(transitionMap[i][2].charAt(0));
                    break;
                }
            }
            if (!isFound && !getCurrentState().isFinal())
                isCrashed = true;
            index++;
        }
    }

    @Override
    public void prevState() {
        boolean isFound = false;
        if(index > 0) {
            index--;
            for (int i = 0; i < transitionMap.length; i++) {
                String curr = getCurrentState().getName();
                if (transitionMap[i][3].equalsIgnoreCase(curr) && transitionMap[i][1].equals(getInput().charAt(index) + "")) {
                    isFound = true;
                    getCurrentState().getStateObject().isActive(false);
                    changeState(transitionMap[i][0]);
                    getCurrentState().getStateObject().isActive(true);
                    unappendOutput();
                    break;
                }
            }
            if (!isFound && !getCurrentState().isFinal())
                isCrashed = true;
        }
    }

    @Override
    public boolean isDone() {
        return getCurrentState().isFinal();
    }

    @Override
    public boolean isCrashed() {
        return isCrashed;
    }
}
