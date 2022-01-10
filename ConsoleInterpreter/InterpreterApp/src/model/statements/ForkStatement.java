package model.statements;

import model.ADTs.ExecutionStack;
import model.ADTs.IDict;
import model.ProgramState;
import model.types.IType;

public class ForkStatement implements IStatement{
    IStatement statement;

    public ForkStatement(IStatement statement){
        this.statement = statement;
    }

    @Override
    public ProgramState execute(ProgramState parentThread) throws Exception {
        return statement == null ? null : new ProgramState(
                new ExecutionStack<IStatement>(),
                parentThread.getSymbolsDict().cloneDict(),
                parentThread.getOutput(),
                parentThread.getFileTable(),
                parentThread.getHeap(),
                statement
        );
    }

    @Override
    public IDict<String, IType> typeCheck(IDict<String, IType> typeEnvironment) throws Exception {
        statement.typeCheck(typeEnvironment.cloneDict());
        return typeEnvironment;
    }

    @Override
    public String toString() {
        return "Fork(" + statement.toString() + ")";
    }
}
