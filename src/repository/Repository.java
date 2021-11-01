package repository;

import model.ProgramState;

import java.util.ArrayList;
import java.util.List;

public class Repository implements IRepository{

    List<ProgramState> programStateList;

    public Repository(){
        programStateList = new ArrayList<>();
    }

    public void addProgramState(ProgramState programState){
        programStateList.add(programState);
    }

    public ProgramState getCurrentProgramState() {
        return programStateList.get(0);
    }
}
