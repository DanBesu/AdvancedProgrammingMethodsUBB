package model.statements;

import model.ADTs.IDict;
import model.ADTs.IStack;
import model.ProgramState;
import model.exceptions.AdtException;
import model.exceptions.EvaluationException;
import model.exceptions.MyException;
import model.expressions.IExpression;
import model.expressions.ValueExpr;
import model.types.IType;
import model.values.IValue;

public class AssignStmt implements IStatement{
    String variableName;
    IExpression expression;

    public AssignStmt(String variableName, IExpression expression) {
        this.variableName = variableName;
        this.expression = expression;
    }
    //    ...

    public String toString(){
        return variableName + "=" + expression.toString();
    }

    public ProgramState execute(ProgramState state) throws MyException, AdtException, EvaluationException {
        IStack<IStatement> executionStack = state.getExecutionStack();
        IDict<String, IValue> symbolsDict = state.getSymbolsDict();

        if(symbolsDict.isDefined(variableName)){
            IValue expressionValue = expression.eval(symbolsDict);
            IType variableType = symbolsDict.lookup(variableName).getType();

            if(expressionValue.getType().equals(variableType)){
                symbolsDict.update(variableName, expressionValue);
            } else {
                throw new MyException( "declared type of variable" + variableName + " and current type of the assigned expression do not match"
                );
            }
        } else{
            throw new MyException("the used variable" + variableName + " was not declared before");
        }

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
