package model.statements;

import model.ADTs.IDict;
import model.ProgramState;
import model.exceptions.EvaluationException;
import model.expressions.IExpression;
import model.types.IType;
import model.values.IValue;
import model.values.ReferenceValue;

public class HeapWritingStatement implements IStatement{

    String variableName;
    IExpression expression;

    public HeapWritingStatement(String variableName, IExpression expression){
        this.variableName = variableName;
        this.expression = expression;
    }

    @Override
    public ProgramState execute(ProgramState state) throws Exception {
        IDict<String, IValue> symbolTable = state.getSymbolsDict();
        IDict<Integer, IValue> heap = state.getHeap();

        if(!symbolTable.isDefined(variableName))
            throw new EvaluationException(variableName + " is not declared in this scope");

        IValue variableValue = symbolTable.lookup(variableName);
        int heapPosition = ((ReferenceValue) variableValue).getHeapAddress();
        if(!heap.isDefined(heapPosition))
            throw new EvaluationException("Undefined variable at address " + heapPosition);

        IValue expressionValue = expression.eval(symbolTable, heap); // todo: (symbolTable, heap)
        IType expressionType = expressionValue.getType();
        IValue referencedValue = heap.lookup(heapPosition);
        IType referencedType = referencedValue.getType();

        if(!expressionType.equals(referencedType))
            throw new EvaluationException("The referenced type of " + variableName + "doesn t match the expression");

        heap.update(heapPosition, expressionValue);
        return null;
    }

    @Override
    public String toString() {
        return "(" + variableName + ") = " + expression.toString() + ";";
    }
}
