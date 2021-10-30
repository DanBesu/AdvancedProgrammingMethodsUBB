package model.ADTs;

import model.exceptions.AdtException;
import model.values.IValue;

import java.util.Collection;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;

public class SymbolsDict implements IDict<String, IValue>{
    Map<String, IValue> dict;

    public SymbolsDict(Map<String, IValue> dict) {
        this.dict = new HashMap<>();
    }

    public int size(){
        return dict.size();
    }

    public void clear(){
        dict.clear();
    }

    public boolean isEmpty(){
        return dict.isEmpty();
    }

    public void add(String key, IValue value){
        dict.put(key, value);
    }

    public void delete(String key){
        dict.remove(key);
    }

    public IValue lookup(String variableName) throws AdtException {
        if(dict.get(variableName) != null)
            return dict.get(variableName);
        throw new AdtException("variable name not found");
    }

    public boolean isDefined(String variableName) {
        return dict.get(variableName) != null;
    }

    public void update(String variableName, IValue iValue) {
        dict.replace(variableName, iValue);
    }

    public String toString(){
        if(dict.isEmpty()) {
            return "{}";
        }

        // todo: solve warning
        String result = "{ ";
        Collection<String> keys = dict.keySet();

        for(String key: keys) {
            result = result + key + " -> " + dict.get(key) + ", ";
        }
        result = result.substring(0, result.length() - 2);
        result += " }";
        return result;
    }
}
