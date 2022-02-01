package model.statements;

import model.ADTs.IDict;
import model.ADTs.IStack;
import model.ProgramState;
import model.exceptions.EvaluationException;
import model.expressions.IExpression;
import model.expressions.VariableExpr;
import model.types.BoolType;
import model.types.IType;

public class ConditionalAssignment implements IStatement{
    String variableName;
    IExpression conditionalExpression;
    IExpression ifTrueExpression;
    IExpression ifFalseExpression;


    public ConditionalAssignment(String variableName,
                                 IExpression conditionalExpression,
                                 IExpression ifTrueExpression,
                                 IExpression ifFalseExpression
                                 ){
        this.variableName = variableName;
        this.conditionalExpression = conditionalExpression;
        this.ifTrueExpression = ifTrueExpression;
        this.ifFalseExpression = ifFalseExpression;
    }

    @Override
    public ProgramState execute(ProgramState state) throws Exception {

        IStack<IStatement> stack = state.getExecutionStack();
        stack.push(new IfStmt(
                this.conditionalExpression,
                new AssignStmt(this.variableName, this.ifTrueExpression),
                new AssignStmt(this.variableName, this.ifFalseExpression)
                )
        );
        return null;
    }

    @Override
    public IDict<String, IType> typeCheck(IDict<String, IType> typeEnv) throws Exception {

        IType conditionType = conditionalExpression.typeCheck(typeEnv);
        IType variableType = new VariableExpr(variableName).typeCheck(typeEnv);
        IType trueExpressionType = ifTrueExpression.typeCheck(typeEnv);
        IType falseExpressionType = ifFalseExpression.typeCheck(typeEnv);

        if(!conditionType.equals(new BoolType()))
            throw new EvaluationException("Conditional Assignment: the condition is not boolean");

        if(!trueExpressionType.equals(variableType) || !falseExpressionType.equals(variableType))
            throw new EvaluationException("Conditional Assignment: the variable type does not match the expression types");

        return typeEnv;
    }

    @Override
    public String toString(){
        return this.variableName +
                " = " +
                this.conditionalExpression.toString() +
                " ? " +
                this.ifTrueExpression.toString() +
                " : " +
                this.ifFalseExpression.toString();
    }
}
