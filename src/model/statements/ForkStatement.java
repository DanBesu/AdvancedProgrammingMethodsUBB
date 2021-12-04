package model.statements;

import model.ADTs.ExecutionStack;
import model.ProgramState;

public class ForkStatement implements IStatement{
    IStatement statement;

    ForkStatement(IStatement statement){
        this.statement = statement;
    }

    @Override
    public ProgramState execute(ProgramState parentThread) throws Exception {
        return statement == null ? null : new ProgramState(
                new ExecutionStack<>(),
                parentThread.getSymbolsDict().cloneDict(),
                parentThread.getOutput(),
                parentThread.getFileTable(),
                parentThread.getHeap(),
                statement
        );
    }

    @Override
    public String toString() {
        return "Fork(" + statement.toString() + ")";
    }
}
