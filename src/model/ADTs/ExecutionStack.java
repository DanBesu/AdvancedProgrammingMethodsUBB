package model.ADTs;

import java.util.Stack;

public class ExecutionStack <TElem> implements IStack<TElem>{
    Stack<TElem> stack;

    public TElem pop() {
        return stack.pop();
    }

    public boolean isEmpty() {
        return stack.isEmpty();
    }

    public void push(TElem element) {
        stack.push(element);
    }

    public String toString(){
        if(stack.isEmpty()) {
            return "{}";
        }

        String result = "{ ";
        for(TElem element: this.stack) {
            result += element.toString() + " | ";
        }
        result = result.substring(0, result.length() - 2);
        result += "}";
        return result;
    }
}
