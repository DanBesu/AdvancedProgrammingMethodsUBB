package model.types;

import model.values.BoolValue;
import model.values.IValue;
import model.values.IntValue;

public class BoolType implements IType{
    public boolean equals(Object another){
        return another instanceof Boolean;
    }
    public String toString(){
        return "bool ";
    }
    public IValue getDefaultValue(){
        return new BoolValue(false);
    }
}
