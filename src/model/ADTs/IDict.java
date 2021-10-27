package model.ADTs;

public interface IDict<String, IValue> {
    IValue lookup(String id);
}
