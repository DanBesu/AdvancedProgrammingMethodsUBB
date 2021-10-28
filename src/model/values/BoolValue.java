package model.values;

import model.types.BoolType;
import model.types.IType;

public class BoolValue implements IValue {

    boolean value;
    public BoolValue(boolean value) {
        this.value = value;
    }

    public boolean getValue(){
        return value;
    }

    public IType getType() {
        return new BoolType();
    }
}
