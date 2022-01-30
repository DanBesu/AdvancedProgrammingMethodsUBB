package repository;

import model.ProgramState;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class Repository implements IRepository{

    List<ProgramState> programStateList;
    String logFilePath;

    public Repository(List<ProgramState> programStateList, String logFilePath){
        this.programStateList = programStateList;
        this.logFilePath = logFilePath;
    }

    public List<ProgramState> getProgramStateList(){
        return programStateList;
    }

    public void setProgramStateList(List<ProgramState> newProgramStateList){
//        System.out.println("setProgramStateList");
//        System.out.println(newProgramStateList);
//        programStateList.clear();
//        System.out.println(" ");
//        System.out.println(" ");
//        System.out.println("setProgramStateList");
//        System.out.println(newProgramStateList);
//        programStateList.addAll(newProgramStateList);
        programStateList = newProgramStateList;
    }

    public void logProgramStateExecution(ProgramState programState) throws IOException {
        PrintWriter logFile = new PrintWriter(new BufferedWriter(new FileWriter(logFilePath, true)));
//        System.out.println("Repo: " + programState.toString());
//        logFile.write(programState.toString());
        logFile.append(programState.toString());
        logFile.close();
    }

    public void clearLogFile() throws Exception {
        PrintWriter logFile = new PrintWriter(new BufferedWriter(new FileWriter(logFilePath, false)));
        logFile.write("");
        logFile.close();
    }
}
