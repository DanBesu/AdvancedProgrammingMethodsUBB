package model.statements;

import model.ADTs.IDict;
import model.ProgramState;
import model.types.IType;

public interface IStatement {
    ProgramState execute(ProgramState state) throws Exception;
    IDict<String, IType> typeCheck(IDict<String, IType> typeEnv) throws Exception;
}
