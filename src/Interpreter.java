import controller.Controller;
import model.ProgramState;
import model.expressions.*;
import model.statements.*;
import model.types.IntType;
import model.types.ReferenceType;
import model.types.StringType;
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

        // Ref int v;new(v,20);Ref Ref int a; new(a,v);print(v);print(a)
        IStatement ex5 = new CompoundStatement(new VariableDeclarationStmt("v",new ReferenceType(new IntType())),
                new CompoundStatement(new HeapAllocationStatement("v", new ValueExpr(new IntValue(20))),
                        new CompoundStatement(new VariableDeclarationStmt("a",new ReferenceType(new ReferenceType(new IntType()))),
                                new CompoundStatement(new HeapAllocationStatement("a",new VariableExpr("v")),
                                        new CompoundStatement(new PrintStmt(new VariableExpr("v")),
                                                new PrintStmt(new VariableExpr("a")))))));

        List<ProgramState> program5 = new ArrayList<>();
        program5.add(new ProgramState(ex5));
        IRepository repository5 = new Repository(program5, "log5.txt");
        Controller controller5 = new Controller(repository5);

        // Ref int v;new(v,20);Ref Ref int a; new(a,v); new(v,30);print(rH(rH(a)))
        IStatement ex6 = new CompoundStatement(new VariableDeclarationStmt("v", new ReferenceType(new IntType())),
                new CompoundStatement(new HeapAllocationStatement("v", new ValueExpr(new IntValue(20))),
                        new CompoundStatement(new VariableDeclarationStmt("a", new ReferenceType(new ReferenceType(new IntType()))),
                                new CompoundStatement(new HeapAllocationStatement("a", new VariableExpr("v")),
                                        new CompoundStatement(new HeapAllocationStatement("v", new ValueExpr(new IntValue(30))),
                                                new PrintStmt(new HeapReadingExpression(new HeapReadingExpression(new VariableExpr("a")))))))));

        List<ProgramState> program6 = new ArrayList<>();
        program6.add(new ProgramState(ex6));
        IRepository repository6 = new Repository(program6, "log6.txt");
        Controller controller6 = new Controller(repository6);

        IStatement ex9 = new CompoundStatement(
                new VariableDeclarationStmt(
                        "v",
                        new IntType()),
                new CompoundStatement(
                        new AssignStmt(
                                "v",
                                new ValueExpr(new IntValue(4))
                        ),
                        new CompoundStatement(
                                new WhileStatement(
                                        new RelationalExpression(">", new VariableExpr("v"), new ValueExpr(new IntValue(0))),
                                        new CompoundStatement(
                                                new PrintStmt(
                                                        new VariableExpr("v")
                                                ),
                                                new AssignStmt(
                                                        "v",
                                                        new ArithmeticExpr('-', new VariableExpr("v"), new ValueExpr( new IntValue(1))))
                                        )
                                ),
                                new PrintStmt(new VariableExpr("v")))
                )
        );
        List<ProgramState> program9 = new ArrayList<>();
        program9.add(new ProgramState(ex9));
        IRepository repository9 = new Repository(program9, "log9.txt");
        Controller controller9 = new Controller(repository9);

        IStatement ex10 = new CompoundStatement(
            new VariableDeclarationStmt("v", new IntType()),
            new CompoundStatement(
                new VariableDeclarationStmt("a", new ReferenceType(new IntType())),
                new CompoundStatement(
                    new AssignStmt("a", new ValueExpr((new IntValue(10)))),
                    new CompoundStatement(
                        new HeapAllocationStatement("a", new ValueExpr( new IntValue(22))),
                        new CompoundStatement(
                            new ForkStatement(
                                new CompoundStatement(
                                    new HeapWritingStatement("a", new ValueExpr(new IntValue(30))),
                                    new CompoundStatement(
                                        new AssignStmt("v", new ValueExpr(new IntValue(32))),
                                        new CompoundStatement(
                                                new PrintStmt(new VariableExpr("v")),
                                                new PrintStmt(new HeapReadingExpression(new VariableExpr("a")))
                                        )
                                    )
                                )
                            ),
                            new CompoundStatement(
                                    new PrintStmt(new VariableExpr("v")),
                                    new PrintStmt(new HeapReadingExpression(new VariableExpr("a")))
                            )
                        )
                    )
                )
            )
        );
        List<ProgramState> program10 = new ArrayList<>();
        program10.add(new ProgramState(ex10));
        IRepository repository10 = new Repository(program10, "log10.txt");
        Controller controller10 = new Controller(repository10);

        TextMenu menu = new TextMenu();
        menu.addCommand(new ExitCommand("x", "exit"));
        menu.addCommand(new RunExample("1", ex1.toString(), controller1));
        menu.addCommand(new RunExample("2", ex2.toString(), controller2));
        menu.addCommand(new RunExample("3", ex3.toString(), controller3));
        menu.addCommand(new RunExample("4", ex4.toString(), controller4));
        menu.addCommand(new RunExample("5", ex5.toString(), controller5));
        menu.addCommand(new RunExample("6", ex6.toString(), controller6));
        menu.addCommand(new RunExample("9", ex9.toString(), controller9));
        menu.addCommand(new RunExample("10", ex10.toString(), controller10));
        menu.show();
    }
}
