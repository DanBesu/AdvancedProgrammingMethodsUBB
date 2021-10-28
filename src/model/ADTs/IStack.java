package model.ADTs;

public interface IStack <TElem>{
//    ...
    TElem pop();
    void push(TElem value);
    boolean isEmpty();
}
