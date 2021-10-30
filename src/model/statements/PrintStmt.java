package model.statements;

import model.ADTs.IDict;
import model.ADTs.IList;
import model.ProgramState;
import model.exceptions.AdtException;
import model.exceptions.EvaluationException;
import model.exceptions.MyException;
import model.expressions.IExpression;
import model.expressions.VariableExpr;
import model.values.IValue;

public class PrintStmt implements IStatement {
    IExpression expression;

    public PrintStmt(IExpression expression) {
        this.expression = expression;
    }

    public String toString(){
        return "print(" + expression.toString() + ")";
    }

    public ProgramState execute(ProgramState state) throws MyException, AdtException, EvaluationException {
        IList<IValue> outputList = state.getOutput();
        IDict<String, IValue> symbolsTable = state.getSymbolsDict();
        IValue expressionValue = expression.eval(symbolsTable);
        outputList.add(expressionValue);
        return state;
    }
}
