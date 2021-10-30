package model.ADTs;

import model.exceptions.AdtException;
import model.values.IValue;

public interface IDict<String, IValue> {
    IValue lookup(String variableName) throws AdtException;
    boolean isDefined(String variableName);
    void update(String variableName, IValue value);
    public int size();
    public void clear();
    public boolean isEmpty();
    public void add(String key, IValue value);
    public void delete(String key);
    public java.lang.String toString();
}
