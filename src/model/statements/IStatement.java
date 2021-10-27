package model.statements;

import model.ProgramState;
import model.exceptions.MyException;

public interface IStatement {
    ProgramState execute(ProgramState state) throws MyException;
    //which is the execution method for a statement.         }
}
