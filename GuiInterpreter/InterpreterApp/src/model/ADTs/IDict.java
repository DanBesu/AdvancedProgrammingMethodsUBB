package model.ADTs;

import model.exceptions.AdtException;

import java.util.Map;

public interface IDict<TKey, TValue> {
    TValue lookup(TKey variableName) throws AdtException;
    boolean isDefined(TKey variableName);
    void update(TKey variableName, TValue value);
    public int size();
    public void clear();
    public boolean isEmpty();
    public void add(TKey key, TValue value);
    public void delete(TKey key);
    public String toString();
    public IDict<TKey, TValue> cloneDict();
    void setContent(Map<TKey, TValue> newContent);
    Map<TKey, TValue> getContent();
}
