package model.statements;

import model.ProgramState;
import model.exceptions.MyException;

public class PrintStmt implements IStatement {
//    Exp exp;
//    ...
    public String toString(){
//        return "print(" + exp.toString()+")";
        return "test";
    }

    public ProgramState execute(ProgramState state) throws MyException {
        // ...
        return state;
    }
    // ...
}

