package model.statements;

import model.ADTs.IDict;
import model.ADTs.IStack;
import model.ProgramState;
import model.exceptions.AdtException;
import model.exceptions.EvaluationException;
import model.exceptions.ExecutionException;
import model.expressions.IExpression;
import model.types.BoolType;
import model.types.IType;
import model.values.BoolValue;
import model.values.IValue;

public class IfStmt implements IStatement{
    IExpression condition;
    IStatement thenS;
    IStatement elseS;

    public IfStmt(IExpression condition, IStatement thenS, IStatement elseS) {
        this.condition = condition;
        this.thenS = thenS;
        this.elseS = elseS;
    }

    public String toString(){
        return "IF("+ condition.toString()+") THEN(" +thenS.toString() + ") ELSE("+ elseS.toString()+"))";
    }

    public ProgramState execute(ProgramState state) throws AdtException, EvaluationException, ExecutionException {
        IDict<String, IValue> symbolsDict = state.getSymbolsDict();
        IDict<Integer, IValue> heap = state.getHeap();
        IValue conditionValue = condition.eval(symbolsDict, heap);

        if(!(conditionValue.getType() instanceof BoolType)){
            throw new ExecutionException("invalid type");
        }

        IStack<IStatement> executionStack = state.getExecutionStack();
        if(((BoolValue) conditionValue).getValue())
            executionStack.push(thenS);
        else
            executionStack.push(elseS);

        return null;
    }

    @Override
    public IDict<String, IType> typeCheck(IDict<String, IType> typeEnv) throws Exception {
        IType conditionType = condition.typeCheck(typeEnv);

        if(!conditionType.equals(new BoolType()))
            throw new EvaluationException("IF: the condition is not boolean");

        thenS.typeCheck(typeEnv.cloneDict());
        elseS.typeCheck(typeEnv.cloneDict());

        return typeEnv;
    }
}
