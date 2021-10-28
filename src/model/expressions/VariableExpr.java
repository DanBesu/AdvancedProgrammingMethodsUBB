package model.expressions;

import model.ADTs.IDict;
import model.exceptions.MyException;
import model.values.IValue;

public class VariableExpr implements IExpression{
    String id;

    public VariableExpr(String variableName) {
    }

    public IValue eval(IDict<String, IValue> table) throws MyException {
        return table.lookup(id);
    }
}
