package model.statements;

import model.ProgramState;
import model.exceptions.MyException;

public class IfStmt implements IStatement{

//    Exp exp;
    IStatement thenS;
    IStatement elseS;
//    ...

//    IfStmt(Exp e, IStatement t, IStatement el){
//        exp = e;
//        thenS = t;
//        elseS = el;
//    }
    public String toString(){
//        return "(IF("+ exp.toString()+") THEN(" +thenS.toString() +")ELSE("+elseS.toString()+"))";
        return null;
    }

    public ProgramState execute(ProgramState state) throws MyException{
        return state;
    }
}
