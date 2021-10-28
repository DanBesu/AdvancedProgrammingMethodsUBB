package controller;

import model.ADTs.IStack;
import model.ProgramState;
import model.exceptions.MyException;
import model.statements.IStatement;
import repository.IRepository;

public class Controller implements IController{

    IRepository repository;
    ProgramState oneStep(ProgramState state) throws MyException {
        IStack<IStatement> stack = state.getStack();

        if(stack.isEmpty())
            throw new MyException("program state stack is empty");

        IStatement currentStatement = stack.pop();
        return currentStatement.execute(state);
    }

    void allStep() throws MyException {
        ProgramState program = repository.getCurrentProgram();
        // print program state
        while(!program.getStack().isEmpty()){
            oneStep(program);
            // print program state
        }
    }
}
