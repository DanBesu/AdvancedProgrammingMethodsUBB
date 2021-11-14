package repository;

import model.ProgramState;

import java.io.IOException;

public interface IRepository {
    ProgramState getCurrentProgramState();
    void addProgramState(ProgramState programState);
    void logProgramStateExecution(ProgramState programState) throws IOException;
    public void clearLogFile() throws Exception;
}
