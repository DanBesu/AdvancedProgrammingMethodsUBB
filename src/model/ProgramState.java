package model;

import model.ADTs.*;
import model.statements.IStatement;
import model.values.IValue;
import model.values.StringValue;

import java.io.BufferedReader;


public class ProgramState {
     IStack<IStatement> executionStack;
     IDict<String, IValue> symbolsDict;
     IList<IValue> output;
     IStatement originalProgram;
     IDict<StringValue, BufferedReader> fileTable;
     IDict<Integer, IValue> heap;

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
         symbolsDict = new SymbolsDict<>();
         output = new OutputList();
         fileTable = new SymbolsDict<>();
         heap = new MyHeap<>();
         this.originalProgram = program;
         this.executionStack.push(program);
    }

    public IStack<IStatement> getExecutionStack() {
        return executionStack;
    }

    public IDict<String, IValue> getSymbolsDict(){
         return symbolsDict;
    }

    public IDict<Integer, IValue> getHeap() {
        return this.heap;
    }


    public IList<IValue> getOutput() {
        return output;
    }

    public IDict<StringValue, BufferedReader> getFileTable() {
        return fileTable;
    }

    public void setFileTable(IDict<StringValue, BufferedReader> fileTable) {
        this.fileTable = fileTable;
    }

    public void setHeap(IDict<Integer, IValue> heap) {
        this.heap = heap;
    }

    public String toString(){
        return "\n >>> Program state: \n" +
                "Execution stack: " + executionStack.toString() + '\n' +
                "Symbols table: " + symbolsDict.toString() + '\n' +
                "Out list: " + output.toString() + '\n' +
                "File table: " + fileTable.toString() + '\n' +
                "Heap: " + heap.toString() + '\n';
    }
}
