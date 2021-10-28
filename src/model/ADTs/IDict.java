package model.ADTs;

import model.exceptions.AdtException;

public interface IDict<String, IValue> {
    IValue lookup(String variableName) throws AdtException;
    boolean isDefined(String variableName);
    void update(String variableName, IValue value);
}
