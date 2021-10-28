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
}
