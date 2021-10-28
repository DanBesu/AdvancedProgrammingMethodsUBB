package model.statements;

import model.ProgramState;
import model.exceptions.AdtException;
import model.exceptions.EvaluationException;
import model.exceptions.MyException;

public interface IStatement {
    ProgramState execute(ProgramState state) throws MyException, AdtException, EvaluationException;
}
