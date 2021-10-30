package model.types;

import model.values.IValue;
import model.values.IntValue;

public class IntType implements IType{
    public boolean equals(Object another){
        return another instanceof IntType;
    }

    public String toString(){
        return "int ";
    }

    public IValue getDefaultValue(){
        return new IntValue(0);
    }
}
