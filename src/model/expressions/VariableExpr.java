package model.expressions;

import model.ADTs.IDict;
import model.exceptions.AdtException;
import model.types.IType;
import model.values.IValue;

public class VariableExpr implements IExpression{
    String variableName;

    public VariableExpr(String variableName) {
        this.variableName = variableName;
    }

    public IValue eval(IDict<String, IValue> table, IDict<Integer, IValue> heap) throws AdtException {
        return table.lookup(variableName);
    }

    public String toString(){
        return variableName;
    }

    @Override
    public IType typeCheck(IDict<String, IType> typeEnv) throws Exception {
        return typeEnv.lookup(variableName);
    }
}
