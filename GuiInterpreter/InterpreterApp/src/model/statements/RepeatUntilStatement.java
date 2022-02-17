package model.statements;

import model.ADTs.IDict;
import model.ADTs.IStack;
import model.ProgramState;
import model.exceptions.EvaluationException;
import model.expressions.IExpression;
import model.types.BoolType;
import model.types.IType;

public class RepeatUntilStatement implements IStatement{

    private IStatement statement;
    private IExpression expression;

    public RepeatUntilStatement(IStatement statement, IExpression expression){
        this.statement = statement;
        this.expression = expression;
    }

    @Override
    public ProgramState execute(ProgramState state) throws Exception {
        IStack<IStatement> stack = state.getExecutionStack();
        stack.push(new WhileStatement(expression, statement, false));
        stack.push(statement);
        return null;
    }

    @Override
    public IDict<String, IType> typeCheck(IDict<String, IType> typeEnv) throws Exception {
        if(!expression.typeCheck(typeEnv).equals(new BoolType()))
            throw new EvaluationException("RepeatUntil: Expression is not a boolean");
        return typeEnv;
    }

    @Override
    public String toString() {
        return "repeat {"
                + statement.toString()
                + "} until ("
                + expression.toString()
                + ");";
    }
}
