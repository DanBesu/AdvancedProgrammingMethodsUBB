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

public class WhileStatement implements IStatement{
    IExpression expression;
    IStatement statement;
    boolean expectedLogicalValue = true;

    public WhileStatement(IExpression expression, IStatement statement){
        this.expression = expression;
        this.statement = statement;
    }

    public WhileStatement(IExpression expression, IStatement statement, boolean expectedLogicalValue) {
        this.expression = expression;
        this.statement = statement;
        this.expectedLogicalValue = expectedLogicalValue;
    }


    public ProgramState execute(ProgramState state) throws Exception {
        IStack<IStatement> stack = state.getExecutionStack();
        IDict<String, IValue> symbolTable = state.getSymbolsDict();
        IDict<Integer, IValue> heap = state.getHeap();
        IValue expressionValue = this.expression.eval(symbolTable, heap);

        if (((BoolValue)expressionValue).getValue() == expectedLogicalValue) {
            stack.push(this);
            return this.statement.execute(state);
        }
        return null;
    }

//    @Override
//    public ProgramState execute2(ProgramState state) throws Exception {
//        IStack<IStatement> stack = state.getExecutionStack();
//        IValue expressionValue = expression.eval(state.getSymbolsDict(), state.getHeap());
//
//        if(!expressionValue.getType().equals(new BoolType())){
//            throw new EvaluationException("while condition is not a boolean");
//        }
//
//        if(((BoolValue) expressionValue).getValue()){
//            stack.push(this);
//            stack.push(statement);
//        }
//        return null;
//    }

    @Override
    public IDict<String, IType> typeCheck(IDict<String, IType> typeEnvironment) throws Exception {
        if (!expression.typeCheck(typeEnvironment).equals(new BoolType()))
            throw new EvaluationException("The condition of WhileStatement is not a boolean!");

        statement.typeCheck(typeEnvironment.cloneDict());
        return typeEnvironment;
    }


    public String toString(){
        return "while (" + expression.toString() + ") {" + statement.toString() + "}";
    }
}
