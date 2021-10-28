package model.ADTs;

public interface IStack <TElem>{
//    ...
    TElem pop();
    void push(TElem element);
    boolean isEmpty();
}
