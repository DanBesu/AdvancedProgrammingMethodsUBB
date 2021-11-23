package model.statements;

import model.ADTs.IStack;
import model.ProgramState;
import model.exceptions.EvaluationException;
import model.expressions.IExpression;
import model.types.BoolType;
import model.values.BoolValue;
import model.values.IValue;

public class WhileStatement implements IStatement{
    IExpression expression;
    IStatement statement;

    public WhileStatement(IExpression expression, IStatement statement){
        this.expression = expression;
        this.statement = statement;
    }

    @Override
    public ProgramState execute(ProgramState state) throws Exception {
        IStack<IStatement> stack = state.getExecutionStack();
        IValue expressionValue = expression.eval(state.getSymbolsDict(), state.getHeap());

        if(!expressionValue.getType().equals(new BoolType())){
            throw new EvaluationException("while condition is not a boolean");
        }

        if(((BoolValue) expressionValue).getValue()){
            stack.push(this);
            stack.push(statement);
        }
        return null;
    }

    public String toString(){
        return "while (" + expression.toString() + ") {" + statement.toString() + "}";
    }
}
