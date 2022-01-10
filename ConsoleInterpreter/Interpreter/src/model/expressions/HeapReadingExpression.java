package model.expressions;

import model.ADTs.IDict;
import model.exceptions.AdtException;
import model.exceptions.EvaluationException;
import model.expressions.IExpression;
import model.types.IType;
import model.types.ReferenceType;
import model.values.IValue;
import model.values.ReferenceValue;

public class HeapReadingExpression implements IExpression{

    IExpression expression;

    public HeapReadingExpression(IExpression expression){
        this.expression = expression;
    }

    @Override
    public IValue eval(IDict<String, IValue> table, IDict<Integer, IValue> heap) throws AdtException, EvaluationException {
        IValue expressionValue = expression.eval(table, heap);
        int heapAddress = ((ReferenceValue) expressionValue).getHeapAddress();

        if(!heap.isDefined(heapAddress))
            throw new EvaluationException("Undefined variable at address" + heapAddress);

        return heap.lookup(heapAddress);
    }

    @Override
    public IType typeCheck(IDict<String, IType> typeEnv) throws Exception {
        IType type = expression.typeCheck(typeEnv);
        // todo: here
        if(!(type instanceof ReferenceType referenceType))
            throw new EvaluationException("the readHeap argument is not a Reference Type");

        return referenceType.getInnerType();
    }

    @Override
    public String toString() {
        return "read(" + expression.toString() + ")";
    }
}
