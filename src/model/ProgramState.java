package model;

import model.ADTs.*;
import model.statements.IStatement;
import model.values.IValue;

public class ProgramState {
     IStack<IStatement> executionStack;
     IDict<String, IValue> symbolsDict;
     IList<IValue> output;
     IStatement originalProgram;

     public ProgramState(IStack<IStatement> executionStack,
                         IDict<String, IValue> symbolsDict,
                         IList<IValue> output,
                         IStatement originalProgram
     ){
         this.executionStack = executionStack;
         this.symbolsDict = symbolsDict;
         this.output = output;
         this.originalProgram = originalProgram;
         this.executionStack.push(originalProgram);
     }

     public ProgramState(IStatement program){
         executionStack = new ExecutionStack<>();
         symbolsDict = new SymbolsDict();
         output = new OutputList();
         this.originalProgram = program;
         this.executionStack.push(program);
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

    public String toString(){
        return "Program state: \n" +
                "Execution stack: " + executionStack.toString() + '\n' +
                "Symbols table: " + symbolsDict.toString() + '\n' +
                "Out list: " + output.toString() + '\n';
    }
}
