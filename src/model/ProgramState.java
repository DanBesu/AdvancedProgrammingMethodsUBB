package model;

import model.ADTs.IDict;
import model.ADTs.IList;
import model.ADTs.IStack;
import model.exceptions.MyException;
import model.statements.IStatement;
import model.values.IValue;

public class ProgramState {
     IStack<IStatement> executionStack;
     IDict<String, IValue> symbolsDict;
     IList<IValue> output;
     IStatement originalProgram;

//    override toString
//    getters / setters

     ProgramState(IStack<IStatement> executionStack,
                  IDict<String, IValue> symbolsDict,
                  IList<IValue> output,
                  IStatement originalProgram
     ){
         this.executionStack = executionStack;
         this.symbolsDict = symbolsDict;
         this.output = output;

//         originalProgram = deepCopy();
//         exeStack.push(program);
     }

    public IStack<IStatement> getExecutionStack() {
        return executionStack;
    }

    public IDict<String, IValue> getSymbolsDict(){
         return symbolsDict;
    }

    public IList<IValue> getOutput() {
        return output;
    }


    public ProgramState execute(ProgramState state) throws MyException {
        return state;
     }



}
