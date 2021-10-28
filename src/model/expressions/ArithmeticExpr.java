package model.expressions;
import model.ADTs.IDict;
import model.exceptions.AdtException;
import model.exceptions.EvaluationException;
import model.exceptions.MyException;
import model.types.IntType;
import model.values.IValue;
import model.values.IntValue;

public class ArithmeticExpr implements IExpression{
    IExpression expression1, expression2;
    char operator; // +, -, *, /

    public ArithmeticExpr(char operator, IExpression expression1, IExpression expression2) {
        this.operator = operator;
        this.expression1 = expression1;
        this.expression2 = expression2;
    }

    public IValue eval(IDict<String, IValue> table) throws MyException, AdtException, EvaluationException {
        IValue value1 = expression1.eval(table);
        if (!value1.getType().equals(new IntType()))
            throw new MyException("Wooow be careful, the first operand is not an Integer");

        IValue value2 = expression2.eval(table);
        if (!value2.getType().equals(new IntType()))
            throw new MyException("Wooow be careful, the second operand is not an Integer");

        IntValue int1 = (IntValue) value1;
        IntValue int2 = (IntValue) value2;

        switch (operator){
        case '+':
            return new IntValue(int1.getVal() + int2.getVal());
        case '-':
            return new IntValue(int1.getVal() - int2.getVal());
        case '*':
            return new IntValue(int1.getVal() * int2.getVal());
        case '/':
            if(int2.getVal() == 0) throw new MyException("<div x=0/>");
            return new IntValue(int1.getVal() / int2.getVal());
        default:
            throw new EvaluationException("invalid operator");
        }
    }
//    ...
}
