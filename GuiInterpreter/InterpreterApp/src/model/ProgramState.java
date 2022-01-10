package model;

import model.ADTs.*;
import model.exceptions.AdtException;
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
    int threadID;
    static int globalThreadCount = 1;

    public ProgramState(IStack<IStatement> executionStack,
                        IDict<String, IValue> symbolsDict,
                        IList<IValue> output,
                        IDict<StringValue, BufferedReader> fileTable,
                        IDict<Integer, IValue> heap,
                        IStatement originalProgram
    ){
         this.executionStack = executionStack;
         this.symbolsDict = symbolsDict;
         this.output = output;
         this.fileTable = fileTable;
         this.heap = heap;
         this.originalProgram = originalProgram;
         this.executionStack.push(originalProgram);
         this.threadID = ProgramState.manageThreadID();
    }

    public ProgramState(IStatement program){
         executionStack = new ExecutionStack<>();
         symbolsDict = new SymbolsDict<>();
         output = new OutputList();
         fileTable = new SymbolsDict<>();
         heap = new MyHeap<>();
         this.originalProgram = program;
         this.executionStack.push(program);
         this.threadID = ProgramState.manageThreadID();
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

    public int getThreadID(){
        return threadID;
    }

    public void setFileTable(IDict<StringValue, BufferedReader> fileTable) {
        this.fileTable = fileTable;
    }

    public void setHeap(IDict<Integer, IValue> heap) {
        this.heap = heap;
    }

     /* Synchronized keyword in Java has to do with thread-safety,
     when multiple threads read or write the same variable
     * It is used to define a block of code
     where multiple threads can access the same variable in a safe way*/

    public static synchronized int manageThreadID(){
        int newThreadID = ProgramState.globalThreadCount;
        ProgramState.globalThreadCount += 1;
        return newThreadID;
    }

    public Boolean isNotCompleted(){
        return !(executionStack.size() == 0);
    }

    public ProgramState oneStep() throws Exception {
        if(executionStack.size() == 0)
            throw new AdtException("program state stack is empty");

        IStatement currentStatement = executionStack.pop();
        return currentStatement.execute(this);
    }

    public String toString(){
        return "\n >>>.................................................................>>> " +
                "Program state: \n" +
                "Thread ID:" + threadID + '\n' +
                "Execution stack: " + executionStack.toString() + '\n' +
                "Symbols table: " + symbolsDict.toString() + '\n' +
                "Out list: " + output.toString() + '\n' +
                "File table: " + fileTable.toString() + '\n' +
                "Heap: " + heap.toString() + '\n';
    }
}
