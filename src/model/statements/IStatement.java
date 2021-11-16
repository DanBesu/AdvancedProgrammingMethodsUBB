package model.statements;

import model.ProgramState;
import model.exceptions.AdtException;
import model.exceptions.EvaluationException;
import model.exceptions.ExecutionException;

import java.io.FileNotFoundException;

public interface IStatement {
    ProgramState execute(ProgramState state) throws Exception;
}
