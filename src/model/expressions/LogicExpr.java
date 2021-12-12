package model.expressions;
import model.ADTs.IDict;
import model.exceptions.AdtException;
import model.exceptions.EvaluationException;
import model.types.BoolType;
import model.types.IType;
import model.values.BoolValue;
import model.values.IValue;

public class LogicExpr implements IExpression{
    IExpression expression1, expression2;
    String operator; // and, or

    public LogicExpr(String operator){
        this.operator = operator;
    }

    public IValue eval(IDict<String, IValue> dict, IDict<Integer, IValue> heap) throws AdtException, EvaluationException {
        IValue value1 = expression1.eval(dict, heap);
        if(!value1.getType().equals(new BoolType()))
            throw new EvaluationException("the first operand is not a boolean");

        IValue value2 = expression2.eval(dict, heap);
        if(!value2.getType().equals(new BoolType()))
            throw new EvaluationException("the second operand is not a boolean");

        BoolValue bool1 = (BoolValue) value1;
        BoolValue bool2 = (BoolValue) value2;

        return switch (operator) {
            case "and" -> new BoolValue(bool1.getValue() && bool2.getValue());
            case "or" -> new BoolValue(bool1.getValue() || bool2.getValue());
            default -> throw new EvaluationException("invalid boolean operator");
        };
    }

    @Override
    public IType typeCheck(IDict<String, IType> typeEnv) throws Exception {
        IType type1 = expression1.typeCheck(typeEnv);
        IType type2 = expression2.typeCheck(typeEnv);

        if(!type1.equals(new BoolType()))
            throw new EvaluationException("the first operand is not a boolean");
        if(!type2.equals(new BoolType()))
            throw new EvaluationException("the second operand is not a boolean");

        return new BoolType();
    }

    public String toString(){
        return expression1.toString() + " " + operator + "  " + expression2.toString();
    }
}
