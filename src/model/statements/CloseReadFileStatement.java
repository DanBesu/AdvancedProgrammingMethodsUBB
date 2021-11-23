package model.statements;

import model.ADTs.IDict;
import model.ProgramState;
import model.exceptions.EvaluationException;
import model.expressions.IExpression;
import model.types.StringType;
import model.values.IValue;
import model.values.StringValue;

import java.beans.Expression;
import java.io.BufferedReader;

public class CloseReadFileStatement implements IStatement{
    private IExpression filePath;

    public CloseReadFileStatement(IExpression filePath){
        this.filePath = filePath;
    }

    @Override
    public ProgramState execute(ProgramState state) throws Exception {
        IDict<String, IValue> symbolTable = state.getSymbolsDict();
        IDict<StringValue, BufferedReader> fileTable = state.getFileTable();
        IDict<Integer, IValue> heap = state.getHeap();

        IValue filePathValue = this.filePath.eval(symbolTable, heap);

        if(!filePathValue.getType().equals(new StringType()))
            throw new EvaluationException("file path should be a string");

        // filePathValue = string => cast is available
        String filePathString = ((StringValue)filePathValue).getValue();
        if(!fileTable.isDefined((StringValue) filePathValue)){
            throw new EvaluationException("file path " + filePathString + "is not defined in the file table");
        }

        BufferedReader fileBuffer = fileTable.lookup((StringValue) filePathValue);
        fileBuffer.close();
        fileTable.delete((StringValue) filePathValue);

        return state;
    }

    public String toString(){
        return "close read " + filePath;
    }
}
