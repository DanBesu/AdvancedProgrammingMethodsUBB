package model.values;

import model.types.IType;

public interface IValue {
    int intDefaultValue = 0;
    boolean booleanDefaultValue = false;
    IType getType();
}
