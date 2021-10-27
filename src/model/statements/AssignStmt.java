package model.statements;

import model.ProgramState;
import model.exceptions.MyException;

public class AssignStmt implements IStatement{

    String id;
//    Exp exp;
//    ...
    public String toString(){
//        return id + "=" + exp.toString();
        return null;
    }

    public ProgramState execute(ProgramState state) throws MyException {
//        MyIStack<IStmt> stk = state.getStk();
//        MyIDictionary<String, Value> symbolsTable = state.getSymTable();

//        if(symTbl.isDefined(id)){
//            Value val = exp.eval(symbolsTable);
//            Type typeID  = (symbolsTable.lookup(id)).getType();
//            if(val.getType()).equals(TypeId){
//                symbolsTable.update(id, val);
//            else
//                throw new MyException(
//                "declared type of variable" +
//                id +
//                " and type of the assigned expression do not match"
//                );
//            }
//        else
//            throw new MyException("the used variable" +id + " was not declared before");
//        }
        return state;
    }
    // ...
}
