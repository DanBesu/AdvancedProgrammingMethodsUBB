package view;

import controller.Controller;
import model.ADTs.*;
import model.ProgramState;
import model.exceptions.ExecutionException;
import model.expressions.ArithmeticExpr;
import model.expressions.ValueExpr;
import model.expressions.VariableExpr;
import model.statements.*;
import model.types.BoolType;
import model.types.IntType;
import model.values.BoolValue;
import model.values.IValue;
import model.values.IntValue;

import java.util.Scanner;

public class UI {
    // ex 1
    // int v; v = 2; Print(v)
    static IStatement ex1 = new CompoundStatement(
            new VariableDeclarationStmt("v",new IntType()),
            new CompoundStatement(
                    new AssignStmt("v", new ValueExpr(new IntValue(2))),
                    new PrintStmt(new VariableExpr("v"))
            )
    );
    // ex2
    // int a; int b; a = 2 + 3*5; b = a + 1; Print(b)
    static IStatement ex2 = new CompoundStatement(
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
    static IStatement ex3 = new CompoundStatement(
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
                                    new PrintStmt(new VariableExpr("v"))
                            )
                    )
            )
    );
    private final Controller controller;

    public UI(){
        controller = new Controller();
    }

    private void createProgramState(String example) throws ExecutionException {
        IStack<IStatement> executionStack = new ExecutionStack<>();
        IDict<String, IValue> symbolsTable = new SymbolsDict();
        IList<IValue> outputList = new OutputList();
        ProgramState programState;

        switch (example){
            case "1" -> programState = new ProgramState(executionStack, symbolsTable, outputList, ex1);
            case "2"-> programState = new ProgramState(executionStack, symbolsTable, outputList, ex2);
            case "3" -> programState = new ProgramState(executionStack, symbolsTable, outputList, ex3);
            default -> throw new ExecutionException("Unexpected value: " + example);
        }

        controller.addProgramState(programState);
    }

    private void executeProgram() {
        try{
            ProgramState programState = controller.allStep();
            System.out.println("Output: ");
            for(int i = 0; i < programState.getOutput().size(); ++i){
                System.out.println(programState.getOutput().getData().get(i));
            }
        }
        catch (Exception error){
            System.out.println(error.toString());
        }
    }

    private void printMenu() {
        System.out.println(".....Menu.....");
        System.out.println("1. Program 1");
        System.out.println("2. Program 2");
        System.out.println("3. Program 3");
        System.out.println("4. Run");
        System.out.println("0. quit");
    }

    public void start() {
        printMenu();
        Scanner scanner = new Scanner(System.in);
        String command;
        boolean isRunning = true;

        while (isRunning){
            command = scanner.nextLine();
            try {
                switch (command) {
                    case "1" -> createProgramState("1");
                    case "2" -> createProgramState("2");
                    case "3" -> createProgramState("3");
                    case "4" -> executeProgram();
                    case "0" -> isRunning = false;
                    default -> System.out.println("Unexpected value: " + command + " please try again.");
                }
            }
            catch (Exception error){
                System.out.println(error.toString());
            }
            // todo: cleanup
            // todo: check exceptions
            // todo: test more
            // todo: throws Exception
        }
    }
}
