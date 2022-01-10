package model.types;

import model.values.IValue;
import model.values.StringValue;

public class StringType implements IType {
    @Override
    public boolean equals(Object another) {
        return (another instanceof StringType);
    }

    @Override
    public IValue getDefaultValue() {
        return new StringValue("");
    }

    public String toString() {
        return "string";
    }
}
