package model.statements;

import model.ProgramState;
import model.exceptions.MyException;

public class NopStmt implements IStatement{

    public NopStmt(){};

    public ProgramState execute(ProgramState state) throws MyException {
        return state;
    }
}
