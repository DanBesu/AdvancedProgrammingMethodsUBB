package model.statements;

import model.ADTs.IDict;
import model.ProgramState;
import model.exceptions.AdtException;
import model.exceptions.EvaluationException;
import model.exceptions.ExecutionException;
import model.expressions.IExpression;
import model.types.IType;
import model.values.IValue;

// a = expr; a = 1; a = 3 + 1; a = true; a = b + 4; a = b or c
public class AssignStmt implements IStatement{
    String variableName;
    IExpression expression;

    public AssignStmt(String variableName, IExpression expression) {
        this.variableName = variableName;
        this.expression = expression;
    }

    public String toString(){
        return variableName + " = " + expression.toString();
    }

    public ProgramState execute(ProgramState state) throws AdtException, EvaluationException, ExecutionException {
        IDict<String, IValue> symbolsDict = state.getSymbolsDict();
        IDict<Integer, IValue> heap = state.getHeap();

        if (!symbolsDict.isDefined(variableName))
            throw new ExecutionException("the used variable " + variableName + " was not declared before");

        IValue expressionValue = expression.eval(symbolsDict, heap);
        IType variableType = symbolsDict.lookup(variableName).getType();

        if (expressionValue.getType().equals(variableType))
            symbolsDict.update(variableName, expressionValue);
        else
            throw new ExecutionException("declared type of variable " + variableName + " and current type of the assigned expression do not match");

        return state;
    }

}
