package repository;

import model.ProgramState;

import java.util.ArrayList;
import java.util.List;

public class Repository implements IRepository{

    List<ProgramState> programStateList;
    String logFilePath;

    public Repository(){
        programStateList = new ArrayList<>();
        logFilePath = "";
    }

    public Repository(String logFilePath){
        programStateList = new ArrayList<>();
        this.logFilePath = logFilePath;
    }

    public Repository(List<ProgramState> programStateList, String logFilePath){
        this.programStateList = programStateList;
        this.logFilePath = logFilePath;
    }

    public void addProgramState(ProgramState programState){
        programStateList.add(programState);
    }

    public ProgramState getCurrentProgramState() {
        return programStateList.get(0);
    }
}
