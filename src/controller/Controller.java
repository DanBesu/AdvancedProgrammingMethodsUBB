package controller;

import com.sun.jdi.Value;
import model.ADTs.IDict;
import model.ADTs.IStack;
import model.ProgramState;
import model.exceptions.AdtException;
import model.exceptions.EvaluationException;
import model.exceptions.ExecutionException;
import model.statements.IStatement;
import model.values.IValue;
import model.values.ReferenceValue;
import repository.IRepository;
import repository.Repository;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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


    public Map<Integer, IValue> safeGarbageCollector(List<Integer> symbolTableAddress, Map<Integer, IValue> heap) {
        return heap.entrySet().stream()
                .filter(e -> symbolTableAddress.contains(e.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public List<Integer> getAddressFromSymbolTable(Collection<IValue> symbolTableValues, Collection<IValue> heap) {
        return Stream.concat(
                        heap.stream()
                                .filter(v -> v instanceof ReferenceValue)
                                .map(v -> {ReferenceValue v1 = (ReferenceValue) v; return v1.getHeapAddress();}),
                        symbolTableValues.stream()
                                .filter(v -> v instanceof ReferenceValue)
                                .map(v -> {ReferenceValue v1 = (ReferenceValue) v; return v1.getHeapAddress();})
                )
                .collect(Collectors.toList());
    }

    public void oneStep(ProgramState state) throws Exception {
        IStack<IStatement> stack = state.getExecutionStack();

        if(stack.isEmpty())
            throw new AdtException("program state stack is empty");

        IStatement currentStatement = stack.pop();
        currentStatement.execute(state);
    }

    public ProgramState allStep() throws Exception {
        repository.clearLogFile();
        ProgramState programState = repository.getCurrentProgramState();
        repository.logProgramStateExecution(programState);
        System.out.println(programState);

        while(!programState.getExecutionStack().isEmpty()){
            oneStep(programState);
            repository.logProgramStateExecution(programState);
            System.out.println(programState);

            programState.getHeap().setContent(
                    (HashMap<Integer, IValue>) safeGarbageCollector(
                            getAddressFromSymbolTable(
                                    programState.getSymbolsDict().getContent().values(),
                                    programState.getHeap().getContent().values()
                            ),
                            programState.getHeap().getContent()
                    )
            );

        }
        return programState;
    }
}
