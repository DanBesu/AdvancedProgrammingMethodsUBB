package controller;

import model.ADTs.IStack;
import model.ProgramState;
import model.exceptions.AdtException;
import model.exceptions.EvaluationException;
import model.exceptions.ExecutionException;
import model.statements.IStatement;
import repository.IRepository;
import repository.Repository;

public class Controller {
    IRepository repository;

    public Controller(){
        repository = new Repository();
    }

    public Controller(IRepository repository){
        this.repository = repository;
    }

    public void addProgramState(ProgramState programState){
        repository.addProgramState(programState);
    }

    public void oneStep(ProgramState state) throws AdtException, EvaluationException, ExecutionException {
        IStack<IStatement> stack = state.getExecutionStack();

        if(stack.isEmpty())
            throw new AdtException("program state stack is empty");

        IStatement currentStatement = stack.pop();
        currentStatement.execute(state);
    }

    public ProgramState allStep() throws AdtException, EvaluationException, ExecutionException {
        ProgramState programState = repository.getCurrentProgramState();
        System.out.println(programState);

        while(!programState.getExecutionStack().isEmpty()){
            oneStep(programState);
            System.out.println(programState);
        }

        return programState;
    }
}
