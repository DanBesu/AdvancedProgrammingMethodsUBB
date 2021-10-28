package model.statements;

import model.ProgramState;
import model.exceptions.MyException;

public class CompoundStatement implements IStatement {
    IStatement first;
    IStatement snd;

    public CompoundStatement(IStatement statement1, IStatement statement2) {
    }
    // ...

    public String toString() {
        return "("+first.toString() + ";" + snd.toString()+")";
    }

    public ProgramState execute(ProgramState state) throws MyException {
//        MyIStack<IStmt> stk=state.getStk() stk.push(snd);
//        stk.push(first);
        return state;
    }
}
