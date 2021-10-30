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
    // ex 1
    // int v; v = 2; Print(v)
    IStatement ex1 = new CompoundStatement(
            new VariableDeclarationStmt("v",new IntType()),
            new CompoundStatement(
                    new AssignStmt("v", new ValueExpr(new IntValue(2))),
                    new PrintStmt(new VariableExpr("v"))
            )
    );

    // ex2
    // int a; int b; a = 2 + 3*5; b = a + 1; Print(b)
    IStatement ex2 = new CompoundStatement(
            new VariableDeclarationStmt("a",new IntType()),
            new CompoundStatement(
                    new VariableDeclarationStmt("b", new IntType()),
                    new CompoundStatement(
                            new AssignStmt(
                                    "a",
                                    new ArithmeticExpr(
                                            '+',
                                            new ValueExpr(new IntValue(2)),
                                            new ArithmeticExpr(
                                                    '*',
                                                    new ValueExpr(new IntValue(3)),
                                                    new ValueExpr(new IntValue(5))
                                            )
                                    )
                            ),
                            new CompoundStatement(
                                    new AssignStmt(
                                            "b",
                                            new ArithmeticExpr(
                                                    '+',
                                                    new VariableExpr("a"),
                                                    new ValueExpr(new IntValue(1)))
                                    ),
                                    new PrintStmt(new VariableExpr("b"))
                            )
                    )
            )
    );

    // ex 3
    // bool a; int v; a = true;
    // If a Then v = 2 Else v = 3;
    // Print(v)
    IStatement ex3 = new CompoundStatement(
            new VariableDeclarationStmt("a", new BoolType()),
            new CompoundStatement(
                    new VariableDeclarationStmt("v", new IntType()),
                    new CompoundStatement(
                            new AssignStmt("a", new ValueExpr(new BoolValue(true))),
                            new CompoundStatement(
                                    new IfStmt(
                                            new VariableExpr("a"),
                                            new AssignStmt("v", new ValueExpr(new IntValue(2))),
                                            new AssignStmt("v", new ValueExpr(new IntValue(3)))
                                    ),
                                    new PrintStmt(new VariableExpr("a"))
                            )
                    )
            )
    );

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
