package model.statements;

import model.ProgramState;
import model.exceptions.MyException;
import model.expressions.IExpression;
import model.expressions.VariableExpr;

public class PrintStmt implements IStatement {
    IExpression expression;

    public PrintStmt(IExpression expression) {
        this.expression = expression;
    }

    // ...

    public String toString(){
        return "print(" + expression.toString() + ")";
    }

    public ProgramState execute(ProgramState state) throws MyException {
        // ...
        return state;
    }
    // ...
}

