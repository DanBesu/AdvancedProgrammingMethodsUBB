package model.ADTs;

import java.util.List;

public interface IList<IValue> {
    public int size();
    public boolean isEmpty();
    public void clear();
    public void add(IValue value);
    public IValue removeFirst() throws Exception;
    public List<IValue> getData();
    public String toString();
}
