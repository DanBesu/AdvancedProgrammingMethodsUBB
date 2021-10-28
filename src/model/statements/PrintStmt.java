package model.statements;

import model.ProgramState;
import model.exceptions.MyException;
import model.expressions.IExpression;
import model.expressions.VariableExpr;

public class PrintStmt implements IStatement {
    public PrintStmt(IExpression expression) {
    }

//        Exp exp;
//        ...
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

