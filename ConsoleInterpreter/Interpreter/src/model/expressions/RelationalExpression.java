package model.expressions;

import model.ADTs.IDict;
import model.exceptions.AdtException;
import model.exceptions.EvaluationException;
import model.types.BoolType;
import model.types.IType;
import model.types.IntType;
import model.values.BoolValue;
import model.values.IValue;
import model.values.IntValue;

public class RelationalExpression implements IExpression{
    IExpression expression1;
    IExpression expression2;
    String operator;

    public RelationalExpression(String operator, IExpression expression1, IExpression expression2){
        this.expression1 = expression1;
        this.expression2 = expression2;
        this.operator = operator;
    }

    @Override
    public IValue eval(IDict<String, IValue> table, IDict<Integer, IValue> heap) throws AdtException, EvaluationException {
        IValue v1 = expression1.eval(table, heap);
        IValue v2 = expression2.eval(table, heap);

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


    @Override
    public IType typeCheck(IDict<String, IType> typeEnv) throws Exception {
        IType type1 = expression1.typeCheck(typeEnv);
        IType type2 = expression2.typeCheck(typeEnv);

        if(!type1.equals(new IntType()))
            throw new EvaluationException("the first operand is not a boolean");
        if(!type2.equals(new IntType()))
            throw new EvaluationException("the second operand is not a boolean");

        return new BoolType();
    }

    public String toString(){
        return expression1.toString() + " " + operator + " " + expression2.toString();
    }
}
