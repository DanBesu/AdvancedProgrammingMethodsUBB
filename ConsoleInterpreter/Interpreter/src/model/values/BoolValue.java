package model.values;

import model.types.BoolType;
import model.types.IType;

public class BoolValue implements IValue {
    boolean value;

    public BoolValue(){
        value = booleanDefaultValue;
    }

    public BoolValue(boolean value) {
        this.value = value;
    }

    public boolean getValue(){
        return value;
    }

    public IType getType() {
        return new BoolType();
    }

    public String toString(){
        return String.valueOf(value);
    }
}
