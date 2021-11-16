package model.values;

import model.types.IType;
import model.types.StringType;
import model.values.IValue;

public class StringValue implements IValue {
    private final String value;

    public StringValue(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object another) {
        return (another instanceof StringValue && ((StringValue) another).getValue().equals(value));
    }

    public String getValue() {
        return value;
    }

    @Override
    public IType getType() {
        return new StringType();
    }

    public String toString() {
        return this.value;
    }
}
