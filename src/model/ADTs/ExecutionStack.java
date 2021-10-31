package model.ADTs;

import model.exceptions.AdtException;

import java.util.ArrayDeque;
import java.util.Deque;

public class ExecutionStack <TElem> implements IStack<TElem>{
    Deque<TElem> stack;

    public ExecutionStack(){
        this.stack = new ArrayDeque<>();
    }

    public int size(){
        return stack.size();
    }

    public TElem pop() throws AdtException {
        if(stack.isEmpty())
            throw new AdtException("empty stack");
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
        StringBuilder result = new StringBuilder("{ ");
        for(TElem element: this.stack) {
            result.append(element.toString()).append(" | ");
        }
        result = new StringBuilder(result.substring(0, result.length() - 2));
        result.append("}");
        return result.toString();
    }
}
