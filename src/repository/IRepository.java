package repository;

import model.ProgramState;

import java.io.IOException;
import java.util.List;

public interface IRepository {
    public List<ProgramState> getProgramStateList();
    public void setProgramStateList(List<ProgramState> newProgramStateList);
    void logProgramStateExecution(ProgramState programState) throws IOException;
    public void clearLogFile() throws Exception;
}
