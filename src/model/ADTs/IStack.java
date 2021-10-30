package model.ADTs;

public interface IStack <TElem>{
    public TElem pop();
    public void push(TElem element);
    public boolean isEmpty();
    public String toString();
}
