package model.statements;

import model.ADTs.IStack;
import model.ProgramState;

public class CompoundStatement implements IStatement {
    IStatement statement1;
    IStatement statement2;

    public CompoundStatement(IStatement statement1, IStatement statement2) {
        this.statement1 = statement1;
        this.statement2 = statement2;
    }

    public String toString() {
        return "( "+ statement1.toString() + "; " + statement2.toString() + " )";
    }

    public ProgramState execute(ProgramState state) {
        IStack<IStatement> executionStack = state.getExecutionStack();
        executionStack.push(statement2);
        executionStack.push(statement1);
        return state;
    }
}
