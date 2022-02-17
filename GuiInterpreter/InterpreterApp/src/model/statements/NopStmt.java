package model.statements;

import model.ADTs.IDict;
import model.ProgramState;
import model.types.IType;

public class NopStmt implements IStatement{
    public NopStmt(){};

    public ProgramState execute(ProgramState state) {
        return state;
    }

    public String toString(){
        return "";
    }

    @Override
    public IDict<String, IType> typeCheck(IDict<String, IType> typeEnv) throws Exception {
        return typeEnv;
    }
}
