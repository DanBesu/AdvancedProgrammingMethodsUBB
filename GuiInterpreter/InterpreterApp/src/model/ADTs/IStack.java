package model.ADTs;

import model.exceptions.AdtException;

import java.util.Deque;

public interface IStack <TElem>{
    public TElem pop() throws AdtException;
    public void push(TElem element);
    public boolean isEmpty();
    public String toString();
    public int size();
    public Deque<TElem> getStack();
}
