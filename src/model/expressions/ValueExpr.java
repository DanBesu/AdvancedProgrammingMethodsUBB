package model.expressions;

import model.ADTs.IDict;
import model.exceptions.MyException;
import model.values.IValue;
import model.values.IntValue;

public class ValueExpr implements IExpression{
    IValue e;

    public ValueExpr(IValue value) {

    }
    //    ...

    public IValue eval(IDict<String, IValue> table) throws MyException {
        return null;
    }
}
