package repository;

import model.ADTs.IList;
import model.ProgramState;
import model.expressions.ArithmeticExpr;
import model.expressions.ValueExpr;
import model.expressions.VariableExpr;
import model.statements.*;
import model.types.BoolType;
import model.types.IntType;
import model.values.BoolValue;
import model.values.IntValue;

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
