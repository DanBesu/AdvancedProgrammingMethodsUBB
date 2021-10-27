package model.statements;

import model.ProgramState;
import model.exceptions.MyException;

public class CompStmt implements IStatement {
    IStatement first;
    IStatement snd;
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
