package model.values;

import model.types.IType;
import model.types.IntType;

public class IntValue implements IValue{
    int value;

    public IntValue(){
        value = intDefaultValue;
    }

    public IntValue(int value){
        this.value = value;
    }

    public int getValue(){
        return value;
    }

    public String toString(){
        return String.valueOf(value) + " ";
    }

    public IType getType() {
        return new IntType();
    }
}
