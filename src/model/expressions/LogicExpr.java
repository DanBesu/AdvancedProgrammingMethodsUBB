package model.expressions;

import model.ADTs.IDict;
import model.exceptions.MyException;
import model.values.IValue;

import java.beans.Expression;

public class LogicExpr implements IExpression{

    Expression e1, e2;
    int op;
//    ...
    public IValue eval(IDict<String, IValue> table) throws MyException {
        return null;
    }
}
