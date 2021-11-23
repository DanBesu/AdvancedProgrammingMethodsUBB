package model.expressions;

import model.ADTs.IDict;
import model.exceptions.AdtException;
import model.exceptions.EvaluationException;
import model.types.IntType;
import model.values.BoolValue;
import model.values.IValue;
import model.values.IntValue;

public class RelationalExpression implements IExpression{
    IExpression e1;
    IExpression e2;
    String operator;

    public RelationalExpression(String operator, IExpression e1, IExpression e2){
        this.e1 = e1;
        this.e2 = e2;
        this.operator = operator;
    }

    @Override
    public IValue eval(IDict<String, IValue> table, IDict<Integer, IValue> heap) throws AdtException, EvaluationException {
        IValue v1 = e1.eval(table, heap);
        IValue v2 = e2.eval(table, heap);

        if(!v1.getType().equals(new IntType()))
            throw new EvaluationException("the first operand is not an integer");
        if(!v2.getType().equals(new IntType()))
            throw new EvaluationException("the second operand is not an integer");

        int intValue1 = ((IntValue)v1).getValue();
        int intValue2 = ((IntValue)v2).getValue();

        return switch (operator) {
            case ">" -> new BoolValue(intValue1 > intValue2);
            case "<" -> new BoolValue(intValue1 < intValue2);
            case ">=" -> new BoolValue(intValue1 >= intValue2);
            case "<=" -> new BoolValue(intValue1 <= intValue2);
            case "==" -> new BoolValue(intValue1 == intValue2);
            case "!=" -> new BoolValue(intValue1 != intValue2);
            default -> throw new ArithmeticException("Invalid operand!");
        };
    }

    public String toString(){
        return e1.toString() + " " + operator + " " + e2.toString();
    }
}
