package model.expressions;

import model.ADTs.IDict;
import model.values.IValue;

public class ValueExpr implements IExpression{
    IValue value;

    public ValueExpr(IValue value) {
        this.value = value;
    }

    public IValue eval(IDict<String, IValue> table) {
        return value;
    }

    public String toString(){
        return value.toString();
    }
}
