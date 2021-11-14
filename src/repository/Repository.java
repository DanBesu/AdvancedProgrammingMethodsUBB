package repository;

import model.ProgramState;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
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

    public void logProgramStateExecution(ProgramState programState) throws IOException {
        PrintWriter logFile = new PrintWriter(new BufferedWriter(new FileWriter(logFilePath, true)));

        logFile.write(programState.toString());
//        logFile.append(programState.toString());
        logFile.close();
    }

    public void clearLogFile() throws Exception {
        PrintWriter logFile = new PrintWriter(new BufferedWriter(new FileWriter(logFilePath, false)));
        logFile.write("");
        logFile.close();
    }
}
