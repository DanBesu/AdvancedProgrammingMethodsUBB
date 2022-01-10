package model.types;

import model.values.IValue;
import model.values.ReferenceValue;

public class ReferenceType implements IType{

    IType innerType;

    public ReferenceType(IType innerType){
        this.innerType = innerType;
    }

    public IType getInnerType() {
        return innerType;
    }

    @Override
    public boolean equals(Object another) {
        if (another instanceof ReferenceType)
            return innerType.equals(((ReferenceType) another).getInnerType());
        return false;
    }

    @Override
    public IValue getDefaultValue() {
        return new ReferenceValue(0, innerType);
    }

    public String toString() {
        return "Ref(" + innerType.toString() + ")";
    }
}
