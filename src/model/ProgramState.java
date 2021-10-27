package model;

import model.ADTs.IDict;
import model.ADTs.IList;
import model.ADTs.IStack;
import model.exceptions.MyException;
import model.statements.IStatement;
import model.values.IValue;

public class ProgramState {
     IStack<IStatement> exeStack;
     IDict<String, IValue> symbolsTable;
     IList<IValue> output;
     IStatement originalProgram;

//    override toString
//    getters / setters

     ProgramState(IStack<IStatement> stack, IDict<String, IValue> sTable, IList<IValue> out, IStatement program){
         exeStack = stack;
         symbolsTable = sTable;
         output = out;
//         originalProgram = deepCopy();
//         exeStack.push(program);
     }

     public ProgramState execute(ProgramState state) throws MyException {
        return state;
     }
}
