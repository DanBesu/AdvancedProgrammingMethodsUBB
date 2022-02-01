package model.statements;


import model.ADTs.IDict;
import model.ADTs.IStack;
import model.ProgramState;
import model.exceptions.EvaluationException;
import model.expressions.IExpression;
import model.types.BoolType;
import model.types.IType;
import model.values.BoolValue;
import model.values.IValue;

// repeat statement until expr
public class Repeat implements IStatement{

    IStatement statement;
    IExpression expression;

    public Repeat(IStatement statement, IExpression expression){
        this.statement = statement;
        this.expression = expression;
    }

    @Override
    public ProgramState execute(ProgramState state) throws Exception {
        IStack<IStatement> stack = state.getExecutionStack();
        IValue expressionValue = expression.eval(state.getSymbolsDict(), state.getHeap());

        if(!expressionValue.getType().equals(new BoolType())){
            throw new EvaluationException("condition is not boolean");
        }

        if(((BoolValue) expressionValue).getValue()){
            stack.push(statement);
            stack.push(new WhileStatement(expression, statement));
        }
        return null;
    };

    @Override
    public IDict<String, IType> typeCheck(IDict<String, IType> typeEnv) throws Exception {
        return null;
    }
}

