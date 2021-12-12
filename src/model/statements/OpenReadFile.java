package model.statements;

import model.ADTs.IDict;
import model.ProgramState;
import model.exceptions.AdtException;
import model.exceptions.EvaluationException;
import model.exceptions.ExecutionException;
import model.expressions.IExpression;
import model.types.IType;
import model.types.StringType;
import model.values.IValue;
import model.values.StringValue;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class OpenReadFile implements IStatement {

    IExpression filePath;

    public OpenReadFile(IExpression expression) {
        this.filePath = expression;
    }

    @Override
    public ProgramState execute(ProgramState state) throws AdtException, EvaluationException, ExecutionException, FileNotFoundException {
        IDict<String, IValue> symbolTable = state.getSymbolsDict();
        IDict<StringValue, BufferedReader> fileTable = state.getFileTable();
        IDict<Integer, IValue> heap = state.getHeap();
        IValue filePathValue = filePath.eval(symbolTable, heap);

        if (!filePathValue.getType().equals(new StringType()))
            throw new EvaluationException("File path should be a string");

        String filePathString = ((StringValue) filePathValue).getValue();

        if (fileTable.isDefined((StringValue) filePathValue))
            throw new EvaluationException("File path " + filePathString + " is already in the file table");

        BufferedReader fileBuffer = new BufferedReader(new FileReader(filePathString));
        fileTable.add((StringValue) filePathValue, fileBuffer);

        return null;
    }

    @Override
    public IDict<String, IType> typeCheck(IDict<String, IType> typeEnv) throws Exception {
        if(!filePath.typeCheck(typeEnv).equals(new StringType()))
            throw new EvaluationException("Open File: The filepath should be a string");
        return typeEnv;
    }

    @Override
    public String toString(){
        return "open read " + filePath;
    }
}
