package model.expressions;

import model.ADTs.IDict;
import model.exceptions.MyException;
import model.types.IntType;
import model.values.IValue;
import model.values.IntValue;

public class ArithmeticExpr implements IExpression{
    IExpression e1;
    IExpression e2;

    // constructor

    int operation; // 1 +, 2 -, 3 *, 4 /

    public ArithmeticExpr(char operation, IExpression expression1, IExpression expression2) {
    }

    @Override
    public IValue eval(IDict<String, IValue> table) throws MyException {
        IValue value1, value2;
        value1 = e1.eval(table);
        if(value1.getType().equals(new IntType())){
            value2 = e2.eval(table);
            if(value2.getType().equals(new IntType())){
                IntValue int1 = (IntValue) value1;
                IntValue int2 = (IntValue) value2;
                int number1 = int1.getVal();
                int number2 = int2.getVal();
                // todo: Syntax: if = switch
                if(operation == 1){
                    return new IntValue(number1 + number2);
                }
                if(operation == 2){
                    return new IntValue(number1 - number2);
                }
                if(operation == 3){
                    return new IntValue(number1 * number2);
                }
                if(operation == 4){
                    if(number2 == 0) throw new MyException("<div x=0/>");
                    return new IntValue(number1 / number2);
                }
            } else {
                throw new MyException("Wooow be careful, the second operand is not an Integer");
            }
        } else {
            throw new MyException("Wooow be careful, the first operand is not an Integer");
        }
        return null;
    }
//    ...

}
