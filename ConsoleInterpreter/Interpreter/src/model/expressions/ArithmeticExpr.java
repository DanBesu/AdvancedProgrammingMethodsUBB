package model.expressions;
import model.ADTs.IDict;
import model.exceptions.AdtException;
import model.exceptions.EvaluationException;
import model.types.IType;
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

    public IValue eval(IDict<String, IValue> table, IDict<Integer, IValue> heap) throws AdtException, EvaluationException {
        IValue value1 = expression1.eval(table, heap);
        if (!value1.getType().equals(new IntType()))
            throw new EvaluationException("Wooow be careful, the first operand is not an Integer");

        IValue value2 = expression2.eval(table, heap);
        if (!value2.getType().equals(new IntType()))
            throw new EvaluationException("Wooow be careful, the second operand is not an Integer");

        IntValue int1 = (IntValue) value1;
        IntValue int2 = (IntValue) value2;

        switch (operator){
        case '+':
            return new IntValue(int1.getValue() + int2.getValue());
        case '-':
            return new IntValue(int1.getValue() - int2.getValue());
        case '*':
            return new IntValue(int1.getValue() * int2.getValue());
        case '/':
            if(int2.getValue() == 0) throw new EvaluationException("<div x=0/>");
            return new IntValue(int1.getValue() / int2.getValue());
        default:
            throw new EvaluationException("invalid operator");
        }
    }

    @Override
    public IType typeCheck(IDict<String, IType> typeEnv) throws Exception {
        IType type1 = expression1.typeCheck(typeEnv);
        IType type2 = expression2.typeCheck(typeEnv);

        if(!type1.equals(new IntType()))
            throw new EvaluationException("the first operand is not an integer");
        if(!type2.equals(new IntType()))
            throw new EvaluationException("the second operand is not an integer");

        return new IntType();
    }

    public String toString(){
        return expression1.toString() + " " + operator + " " + this.expression2.toString();
    }
}
