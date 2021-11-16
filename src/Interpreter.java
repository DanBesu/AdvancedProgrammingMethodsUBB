import controller.Controller;
import model.ADTs.IDict;
import model.ADTs.IList;
import model.ADTs.IStack;
import model.ProgramState;
import model.expressions.ArithmeticExpr;
import model.expressions.RelationalExpression;
import model.expressions.ValueExpr;
import model.expressions.VariableExpr;
import model.statements.*;
import model.types.BoolType;
import model.types.IntType;
import model.types.StringType;
import model.values.BoolValue;
import model.values.IValue;
import model.values.IntValue;
import model.values.StringValue;
import repository.IRepository;
import repository.Repository;
import view.ExitCommand;
import view.RunExample;
import view.TextMenu;

import java.util.ArrayList;
import java.util.List;

public class Interpreter {
    public void start() {
        // ex 1
        // int v; v = 2; Print(v)
        IStatement ex1 = new CompoundStatement(
                new VariableDeclarationStmt("v",new IntType()),
                new CompoundStatement(
                        new AssignStmt("v", new ValueExpr(new IntValue(2))),
                        new PrintStmt(new VariableExpr("v"))
                )
        );
        List<ProgramState> program1 = new ArrayList<>();
        program1.add(new ProgramState(ex1));
        IRepository repository1 = new Repository(program1, "log1.txt");
        Controller controller1 = new Controller(repository1);

        // ex2
        // int a; int b; a = 2 + 3*5; b = a + 1; Print(b)
        IStatement ex2 = new CompoundStatement(
                new VariableDeclarationStmt("a", new IntType()),
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
        List<ProgramState> program2 = new ArrayList<>();
        program2.add(new ProgramState(ex2));
        IRepository repository2 = new Repository(program2, "log2.txt");
        Controller controller2 = new Controller(repository2);

        // ex 3
        // bool a; int v; a = true;
        // If a Then v = 2 Else v = 3;
        // Print(v)
        IStatement ex3 = new CompoundStatement(
                new VariableDeclarationStmt("a", new IntType()),
                new CompoundStatement(
                        new VariableDeclarationStmt("v", new IntType()),
                        new CompoundStatement(
                                new AssignStmt("a", new ValueExpr(new IntValue(3))),
                                new CompoundStatement(
                                        new IfStmt(
                                                new RelationalExpression(">", new ValueExpr(new IntValue(4)), new VariableExpr("a")),
                                                new AssignStmt("v", new ValueExpr(new IntValue(2))),
                                                new AssignStmt("v", new ValueExpr(new IntValue(3)))
                                        ),
                                        new PrintStmt(new VariableExpr("v"))
                                )
                        )
                )
        );
        List<ProgramState> program3 = new ArrayList<>();
        program3.add(new ProgramState(ex3));
        IRepository repository3 = new Repository(program3, "log3.txt");
        Controller controller3 = new Controller(repository3);

        IStatement ex4= new CompoundStatement(
                new VariableDeclarationStmt("varf", new StringType()),
                new CompoundStatement(
                        new AssignStmt(
                                "varf" ,
                                new ValueExpr(
                                new StringValue("test.in"))),
                        new CompoundStatement(
                                new OpenReadFile(new VariableExpr("varf")),
                                new CompoundStatement(
                                        new VariableDeclarationStmt("varc",new IntType()),
                                        new CompoundStatement(
                                                new ReadFileStatement(
                                                        new VariableExpr("varf"),
                                                        new StringValue("varc")
                                                ),
                                                new CompoundStatement(
                                                        new ReadFileStatement(
                                                                new VariableExpr("varf"),
                                                                new StringValue("varc")
                                                        ),
                                                        new CompoundStatement(
                                                                new PrintStmt(new VariableExpr("varc")),
                                                                new CloseReadFileStatement(
                                                                        new VariableExpr("varf")
                                                                )
                                                        )
                                                )
                                        )
                                )
                        )
                )
        );
        List<ProgramState> program4 = new ArrayList<>();
        program4.add(new ProgramState(ex4));
        IRepository repository4 = new Repository(program4, "log4.txt");
        Controller controller4 = new Controller(repository4);

        TextMenu menu = new TextMenu();
        menu.addCommand(new ExitCommand("x", "exit"));
        menu.addCommand(new RunExample("1", ex1.toString(), controller1));
        menu.addCommand(new RunExample("2", ex2.toString(), controller2));
        menu.addCommand(new RunExample("3", ex3.toString(), controller3));
        menu.addCommand(new RunExample("4", ex4.toString(), controller4));
        menu.show();
    }
}
