package model.statements;

import model.ProgramState;
import model.exceptions.MyException;
import model.types.IType;
import model.types.IntType;

public class VariableDeclarationStmt implements IStatement{
    String name;

    public VariableDeclarationStmt(String name, IType type) {
    }
//    Type type;
//    ...

    public ProgramState execute(ProgramState state) throws MyException {
        return null;
    }
}
