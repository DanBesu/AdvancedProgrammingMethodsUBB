package model.types;

import model.values.IValue;

public interface IType {
    IValue getDefaultValue();
    boolean equals(Object another);
}
