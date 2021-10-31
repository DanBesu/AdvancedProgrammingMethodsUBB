package model.statements;

import model.ProgramState;

public class NopStmt implements IStatement{
    public NopStmt(){};

    public ProgramState execute(ProgramState state) {
        return state;
    }
}
