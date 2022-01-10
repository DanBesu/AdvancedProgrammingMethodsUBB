package model.statements;

import model.ADTs.IDict;
import model.ADTs.IList;
import model.ProgramState;
import model.exceptions.AdtException;
import model.exceptions.EvaluationException;
import model.expressions.IExpression;
import model.types.IType;
import model.values.IValue;

public class PrintStmt implements IStatement {
    IExpression expression;

    public PrintStmt(IExpression expression) {
        this.expression = expression;
    }

    public String toString(){
        return "print(" + expression.toString() + ")";
    }

    public ProgramState execute(ProgramState state) throws AdtException, EvaluationException {
        IList<IValue> outputList = state.getOutput();
        IDict<String, IValue> symbolsTable = state.getSymbolsDict();
        IDict<Integer, IValue> heap = state.getHeap();

        IValue expressionValue = expression.eval(symbolsTable, heap);
        outputList.add(expressionValue);
        return null;
    }

    @Override
    public IDict<String, IType> typeCheck(IDict<String, IType> typeEnvironment) throws Exception {
        expression.typeCheck(typeEnvironment);
        return typeEnvironment;
    }
}
