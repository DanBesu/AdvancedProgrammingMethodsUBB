package model.ADTs;

import model.exceptions.AdtException;
import model.values.IValue;

import java.util.ArrayList;
import java.util.List;

public class OutputList implements IList<IValue>{

    List<IValue> list;
    public OutputList(){
        this.list = new ArrayList<IValue>();
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    public void add(IValue value) {
        list.add(value);
    }

    @Override
    public IValue removeFirst() throws Exception {
        if(list.isEmpty())
            throw new AdtException("the list is empty");
        return list.remove(0);
    }

    @Override
    public List<IValue> getData() {
        return list;
    }

    public String toString(){
        if(list.isEmpty()) {
            return "[]";
        }
        // todo: solve warning
        String result = "[ ";
        for(IValue element: this.list) {
            result = result + element.toString() + ", ";
        }
        result = result.substring(0, result.length() - 2);
        result = result + " ]";
        return result;
    }
}
