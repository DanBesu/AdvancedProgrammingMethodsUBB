package model.statements;

import model.ADTs.IDict;
import model.ProgramState;
import model.exceptions.EvaluationException;
import model.expressions.IExpression;
import model.types.IType;
import model.types.IntType;
import model.types.StringType;
import model.values.IValue;
import model.values.IntValue;
import model.values.StringValue;

import java.io.BufferedReader;

public class ReadFileStatement implements IStatement{

    private IExpression filePath;
    private String variableName;

    public ReadFileStatement(IExpression filePath, StringValue variableName){
        this.filePath = filePath;
        this.variableName = variableName.getValue();
    }

    @Override
    public ProgramState execute(ProgramState state) throws Exception {
        IDict<String, IValue> symbolsTable = state.getSymbolsDict();

        if(!symbolsTable.isDefined(variableName))
            throw new EvaluationException("variable is not declared");

        if (!symbolsTable.lookup(this.variableName).getType().equals(new IntType())) {
            throw new EvaluationException(this.variableName + " is not an integer");
        }

        IDict<StringValue, BufferedReader> fileTable = state.getFileTable();
        IDict<Integer, IValue> heap = state.getHeap();
        IValue filePathValue = this.filePath.eval(symbolsTable, heap);

        if(!filePathValue.getType().equals(new StringType())){
            throw new EvaluationException("the file path should be a string");
        }
        // file path = StringValue => cast is available
        String filePathString = ((StringValue)filePathValue).getValue();
        if(!fileTable.isDefined((StringValue) filePathValue)){
            throw new EvaluationException("File path " + filePathString + " is not defined in the file table");
        }

        BufferedReader fileBuffer = fileTable.lookup((StringValue)filePathValue);
        String currentLine = fileBuffer.readLine();

        if(currentLine == null)
            symbolsTable.update(this.variableName, new IntValue(0));
        else
            symbolsTable.update(this.variableName, new IntValue(Integer.parseInt(currentLine)));

        return null;
    }

    @Override
    public IDict<String, IType> typeCheck(IDict<String, IType> typeEnv) throws Exception {
        if(!typeEnv.lookup(variableName).equals(new IntType()))
            throw new EvaluationException("Read file: " + variableName + " is not an integer");
        if(!filePath.typeCheck(typeEnv).equals(new StringType()))
            throw new EvaluationException("Read file: the file path should be a string");

        return typeEnv;
    }

    public String toString(){
        return "read file " + filePath + " " + variableName;
    }
}
