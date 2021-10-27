package model.statements;

import model.ProgramState;
import model.exceptions.MyException;

public class VariableDeclarationStmt implements IStatement{
    String name;
//    Type type;
//    ...

    public ProgramState execute(ProgramState state) throws MyException {
        return null;
    }
}
