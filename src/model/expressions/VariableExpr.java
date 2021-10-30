package model.expressions;

import model.ADTs.IDict;
import model.exceptions.AdtException;
import model.exceptions.MyException;
import model.values.IValue;

public class VariableExpr implements IExpression{
    String variableName;

    public VariableExpr(String variableName) {
        this.variableName = variableName;
    }

    public IValue eval(IDict<String, IValue> table) throws AdtException {
        return table.lookup(variableName);
    }

    public String toString(){
        return variableName + " ";
    }
}
