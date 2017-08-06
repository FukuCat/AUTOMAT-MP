package engine.model.state.pushdownautomata;

import java.util.ArrayList;

public class Stack {
    private ArrayList<String> stack;

    public Stack(){
        stack = new ArrayList<>();
    }

    public String peek(){return stack.size() > 0 ? stack.get(stack.size() - 1):"";}
    public String pop(){return stack.size() > 0 ? stack.remove(stack.size() - 1): ""; }
    public void push(char value){
        stack.add(value+"");
    }
    public String getString(){
        StringBuilder sb = new StringBuilder();
        for(String s : stack)
            sb.append(s);
        return sb.toString();
    }
}
