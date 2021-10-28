package model.ADTs;

import model.exceptions.AdtException;
import model.values.IValue;

import java.util.Dictionary;

public class SymbolsDict implements IDict<String, IValue>{
    Dictionary<String, IValue> dict;

    public SymbolsDict(Dictionary<String, IValue> dict) {
        this.dict = dict;
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
        dict.put(variableName, iValue);
        // todo: this might not work
    }
}
