package model.expressions;

import model.ADTs.IDict;
import model.exceptions.AdtException;
import model.exceptions.EvaluationException;
import model.exceptions.MyException;
import model.values.IValue;

public interface IExpression {
    IValue eval(IDict<String, IValue> table) throws MyException, AdtException, EvaluationException;
}
