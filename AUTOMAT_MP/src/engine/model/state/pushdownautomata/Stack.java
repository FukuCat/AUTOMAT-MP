package engine.model.state.pushdownautomata;

import engine.drawable.StackObject;
import engine.drawable.StateObject;

import java.util.ArrayList;

public class Stack {
    private ArrayList<String> stack;
    private StackObject stackObject;

    public Stack(){
        stack = new ArrayList<>();
    }

    public void clear(){stack.clear();}
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

    public StackObject getStackObject() {
        if (stackObject == null) {
            stackObject = new StackObject(this);
        }
        return stackObject;
    }
}
