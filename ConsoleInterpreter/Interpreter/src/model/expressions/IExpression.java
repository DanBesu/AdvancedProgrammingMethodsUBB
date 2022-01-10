package model.expressions;
import model.ADTs.IDict;
import model.exceptions.AdtException;
import model.exceptions.EvaluationException;
import model.types.IType;
import model.values.IValue;

public interface IExpression {
    IValue eval(IDict<String, IValue> table, IDict<Integer, IValue> heap) throws AdtException, EvaluationException;
    String toString();
    IType typeCheck(IDict<String, IType> typeEnv) throws Exception;
}
