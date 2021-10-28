package model.expressions;
import model.ADTs.IDict;
import model.exceptions.AdtException;
import model.exceptions.EvaluationException;
import model.exceptions.MyException;
import model.types.BoolType;
import model.values.BoolValue;
import model.values.IValue;

public class LogicExpr implements IExpression{
    IExpression expression1, expression2;
    String operator; // and, or

    public LogicExpr(String operator){
        this.operator = operator;
    }

    public IValue eval(IDict<String, IValue> dict) throws MyException, AdtException, EvaluationException {
        IValue value1 = expression1.eval(dict);
        if(!value1.getType().equals(new BoolType()))
            throw new EvaluationException("invalid boolean operand 1");

        IValue value2 = expression2.eval(dict);
        if(!value2.getType().equals(new BoolType()))
            throw new EvaluationException("invalid boolean operand 2");

        BoolValue bool1 = (BoolValue) value1;
        BoolValue bool2 = (BoolValue) value2;

        return switch (operator) {
            case "and" -> new BoolValue(bool1.getValue() && bool2.getValue());
            case "or" -> new BoolValue(bool1.getValue() || bool2.getValue());
            default -> throw new EvaluationException("invalid boolean operator");
        };
    }
//    ...
}
