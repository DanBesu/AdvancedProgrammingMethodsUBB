package model.statements;

import model.ADTs.IDict;
import model.ADTs.MyHeap;
import model.ProgramState;
import model.exceptions.EvaluationException;
import model.expressions.IExpression;
import model.types.IType;
import model.types.ReferenceType;
import model.values.IValue;
import model.values.ReferenceValue;

public class HeapAllocationStatement implements IStatement{
    String variableName;
    IExpression expression;

    public HeapAllocationStatement(String variableName, IExpression expression){
        this.variableName = variableName;
        this.expression = expression;
    }

    @Override
    public ProgramState execute(ProgramState state) throws Exception {
        IDict<String, IValue> symbolTable = state.getSymbolsDict();
        IDict<Integer, IValue> heap = state.getHeap();

        if(!symbolTable.isDefined(variableName))
            throw new EvaluationException(variableName + " is not declared in this scope");
        if(!(symbolTable.lookup(variableName).getType() instanceof ReferenceType))
            throw new EvaluationException("Invalid type for" + variableName + ". It should be ReferenceType");

        IValue variableValue = symbolTable.lookup(variableName);
        IValue expressionValue = expression.eval(symbolTable, heap);
        IType expressionType = expressionValue.getType();
        IType referencedType = ((ReferenceValue)variableValue).getReferenceType();

        if(!referencedType.equals(expressionType)){
            throw new EvaluationException("Expression couldn't be evaluated to " + referencedType);
        }

        int newPositionHeap = ((MyHeap<Integer, IValue>)heap).getFirstFreeLocation();
        heap.add(newPositionHeap, expressionValue);

        symbolTable.update(variableName, new ReferenceValue(newPositionHeap, referencedType));
        return null;
    }

    @Override
    public IDict<String, IType> typeCheck(IDict<String, IType> typeEnv) throws Exception {
        IType variableType = typeEnv.lookup(variableName);
        IType expressionType = expression.typeCheck(typeEnv);

        if(!variableType.equals(new ReferenceType(expressionType)))
            throw new EvaluationException("New: The variable name and the expression have different types");

        return typeEnv;
    }

    @Override
    public String toString() {
        return "new(" + variableName + ", " + expression.toString() + ")";
    }
}
