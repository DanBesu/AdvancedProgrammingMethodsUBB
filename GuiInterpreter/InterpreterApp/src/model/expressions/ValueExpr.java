package model.expressions;

import model.ADTs.IDict;
import model.types.IType;
import model.values.IValue;

public class ValueExpr implements IExpression{
    IValue value;

    public ValueExpr(IValue value) {
        this.value = value;
    }

    public IValue eval(IDict<String, IValue> table, IDict<Integer, IValue> heap) {
        return value;
    }

    public String toString(){
        return value.toString();
    }

    @Override
    public IType typeCheck(IDict<String, IType> typeEnv) throws Exception {
        return value.getType();
    }
}
