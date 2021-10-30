package controller;

import model.ADTs.IStack;
import model.ProgramState;
import model.exceptions.AdtException;
import model.exceptions.EvaluationException;
import model.exceptions.ExecutionException;
import model.exceptions.MyException;
import model.statements.IStatement;
import repository.IRepository;
import repository.Repository;

public class Controller implements IController{

    IRepository repository;

    public Controller(){
        repository = new Repository();
    }

    public void addProgramState(ProgramState programState){
        repository.addProgramState(programState);
    }

    ProgramState oneStep(ProgramState state) throws MyException, AdtException, EvaluationException, ExecutionException {
        IStack<IStatement> stack = state.getExecutionStack();

        if(stack.isEmpty())
            throw new MyException("program state stack is empty");

        IStatement currentStatement = stack.pop();
        return currentStatement.execute(state);
    }

    void allStep() throws MyException, AdtException, EvaluationException, ExecutionException {
        ProgramState programState = repository.getCurrentProgramState();
        System.out.println(programState);
        while(!programState.getExecutionStack().isEmpty()){
            oneStep(programState);
            System.out.println(programState);
        }
    }
}
