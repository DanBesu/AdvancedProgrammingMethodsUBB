package model.ADTs;

import model.exceptions.AdtException;
import model.values.IValue;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class SymbolsDict<TKey, TValue> implements IDict<TKey, TValue>{
    Map<TKey, TValue> dict;

    public SymbolsDict() {
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

    public void add(TKey key, TValue value){
        dict.put(key, value);
    }

    public void delete(TKey key){
        dict.remove(key);
    }

    public TValue lookup(TKey variableName) throws AdtException {
        if(dict.get(variableName) != null)
            return dict.get(variableName);
        throw new AdtException("variable name not found");
    }

    public boolean isDefined(TKey variableName) {
        return dict.get(variableName) != null;
    }

    public void update(TKey variableName, TValue iValue) {
        dict.replace(variableName, iValue);
    }

    public String toString(){
        if(dict.isEmpty()) {
            return "{-}";
        }
        StringBuilder result = new StringBuilder("{\n");
        Collection<TKey> keys = dict.keySet();

        for(TKey key: keys) {
            result.append("    ").append(key).append(" -> ").append(dict.get(key)).append('\n');
        }
        result = new StringBuilder(result.substring(0, result.length()));
        result.append(" }");
        return result.toString();
    }
}
