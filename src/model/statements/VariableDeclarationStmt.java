package model.statements;

import model.ADTs.IDict;
import model.ADTs.IStack;
import model.ProgramState;
import model.exceptions.EvaluationException;
import model.exceptions.MyException;
import model.types.IType;
import model.types.IntType;
import model.values.IValue;

// int a; bool a;
public class VariableDeclarationStmt implements IStatement{
    String name;
    IType type;

    public VariableDeclarationStmt(String name, IType type) {
        this.name = name;
        this.type = type;
    }

    public ProgramState execute(ProgramState state) throws MyException, EvaluationException {
        IStack<IStatement> executionStack = state.getExecutionStack();
        IDict<String, IValue> symbolsDict = state.getSymbolsDict();

        if(symbolsDict.isDefined(name))
            throw new EvaluationException("this variable name has been already declared, try another one");

        symbolsDict.update(name, type.getDefaultValue());

        return state;
    }

    public String toString(){
        return type.toString() + " " + name + "; ";
    }
}
