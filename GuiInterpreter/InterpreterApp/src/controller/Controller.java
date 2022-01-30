package controller;

import model.ProgramState;
import model.values.IValue;
import model.values.ReferenceValue;
import repository.IRepository;
import repository.Repository;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Controller {
    IRepository repository;
    ExecutorService executor;

    public Controller(IRepository repository){
        this.repository = repository;
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

    public List<ProgramState> removeCompletedPrograms(List<ProgramState> initialList) {
        return initialList.stream().filter(p -> p.isNotCompleted()).collect(Collectors.toList());
    }

    public void oneStepExecution() {
        executor = Executors.newFixedThreadPool(2);

        removeCompletedPrograms(repository.getProgramStateList());

        List<ProgramState> programsList = repository.getProgramStateList();

        if (programsList.size() > 0) {
            try {
                System.out.println("here");
                oneStepForAllPrograms(repository.getProgramStateList());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

            programsList.forEach(p -> {
                try {
                    repository.logProgramStateExecution(p);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            });
        }
    }

    public void oneStepForAllPrograms(List<ProgramState> programStateList) throws InterruptedException {
        // print programState in file
//        programStateList.forEach(programState -> {
//            try {
//                repository.logProgramStateExecution(programState);
////                System.out.println(programState.toString());
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        });
        // run concurrently one step for each of existing ProgramStates
        // 1. prepare the list of callables
        List<Callable<ProgramState>> callList = programStateList // Callable = asynchronous task which can be executed by a separate thread
                .stream()
                .map((ProgramState p) -> (Callable<ProgramState>)(p::oneStep))
                .collect(Collectors.toList());
        // 2. start the execution of the callables
        // returns the list of new created ProgramStates
        List<ProgramState> newProgramList = executor.invokeAll(callList)
                .stream()
                .map(future -> {
                    try {
                        return future.get();
                    } catch (Exception e) {
                        throw new RuntimeException(e.getMessage());
                    }
                })
                .filter(p -> p != null)
                .collect(Collectors.toList());
        // 3. add the new created threads to the list of existing threads
        programStateList.addAll(newProgramList);
        // 4. print into the log file after execution
        programStateList.forEach(programState -> {
            try {
                repository.logProgramStateExecution(programState);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        // 5. save the current programs in the repository
        System.out.println("before set: " + programStateList);
        repository.setProgramStateList(programStateList);
    }

    public void allStep() throws Exception {
        repository.clearLogFile();
        executor = Executors.newFixedThreadPool(2);
        // remove the completed programs
        List<ProgramState> programStateList = removeCompletedThreads(repository.getProgramStateList());
        while(programStateList.size() > 0){
            programStateList.get(0).getHeap().setContent(
                    (HashMap<Integer, IValue>) safeGarbageCollector(
                            getAddressFromSymbolTable(
                                    programStateList.get(0).getSymbolsDict().getContent().values(),
                                    programStateList.get(0).getHeap().getContent().values()
                            ),
                            programStateList.get(0).getHeap().getContent()
                    )
            );
            oneStepForAllPrograms(programStateList);
            // remove the completed programs
            programStateList = removeCompletedThreads(repository.getProgramStateList());
        }
        executor.shutdownNow();
        repository.setProgramStateList(programStateList);
    }

    public List<ProgramState> removeCompletedThreads(List<ProgramState> initialList) {
        return initialList.stream().filter(ProgramState::isNotCompleted).collect(Collectors.toList());
    }

    public IRepository getRepository() {
        return repository;
    }
}
