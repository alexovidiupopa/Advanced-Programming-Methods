package View;

import Controller.Controller;
import Model.Expressions.ArithmeticExpression;
import Model.Expressions.ValueExpression;
import Model.Expressions.VariableExpression;
import Model.ProgramState.ProgramState;
import Model.Statements.*;
import Model.Types.BoolType;
import Model.Types.IntType;
import Model.Types.StringType;
import Model.Values.BoolValue;
import Model.Values.IntValue;
import Model.Values.StringValue;
import Repository.IRepository;
import Repository.Repository;

public class Interpreter {
    public static void main(String[] args){
        IStatement ex1= new CompoundStatement(new VariableDeclarationStatement("v",new IntType()), new CompoundStatement(new AssignmentStatement("v",new ValueExpression(new IntValue(2))), new PrintStatement(new VariableExpression("v"))));
        IStatement ex2 = new CompoundStatement( new VariableDeclarationStatement("a",new IntType()),  new CompoundStatement(new VariableDeclarationStatement("b",new IntType()),
                new CompoundStatement(new AssignmentStatement("a", new ArithmeticExpression('+',new ValueExpression(new IntValue(2)),new ArithmeticExpression('*',new ValueExpression(new IntValue(3)),
                        new ValueExpression(new IntValue(5))))),  new CompoundStatement(new AssignmentStatement("b",new ArithmeticExpression('+',new VariableExpression("a"),
                        new ValueExpression(new IntValue(1)))), new PrintStatement(new VariableExpression("b"))))));
        IStatement ex3 = new CompoundStatement(new VariableDeclarationStatement("a",new BoolType()), new CompoundStatement(new VariableDeclarationStatement("v", new IntType()),new CompoundStatement(new AssignmentStatement("a", new ValueExpression(new BoolValue(true)))
                , new CompoundStatement(new IfStatement(new VariableExpression("a"),new AssignmentStatement("v",new ValueExpression(new IntValue(2))), new AssignmentStatement("v", new ValueExpression(new IntValue(3)))), new PrintStatement(new VariableExpression("v"))))));
        IStatement ex4 = new CompoundStatement(
                new VariableDeclarationStatement("varf",new StringType()),new CompoundStatement(
                        new AssignmentStatement("varf",new ValueExpression(new StringValue("test.in"))),new CompoundStatement(
                                new OpenFileStatement(new VariableExpression("varf")),new CompoundStatement(
                                        new VariableDeclarationStatement("varc",new IntType()),new CompoundStatement(
                                                new ReadFileStatement(new VariableExpression("varf"),"varc"),new CompoundStatement(
                                                        new PrintStatement(new VariableExpression("varc")),new CompoundStatement(
                        new ReadFileStatement(new VariableExpression("varf"),"varc") ,new CompoundStatement(new PrintStatement(new VariableExpression("varc")),new CloseFileStatement(new VariableExpression("varf"))))))))));

        ProgramState prog1 = new ProgramState(ex1);
        IRepository repo1 = new Repository("log1.txt");
        Controller controller1 = new Controller(repo1);
        controller1.addProgram(prog1);

        ProgramState prog2 = new ProgramState(ex2);
        IRepository repo2 = new Repository("log2.txt");
        Controller controller2 = new Controller(repo2);
        controller2.addProgram(prog2);

        ProgramState prog3 = new ProgramState(ex3);
        IRepository repo3 = new Repository("log3.txt");
        Controller controller3 = new Controller(repo3);
        controller3.addProgram(prog3);

        ProgramState prog4 = new ProgramState(ex4);
        IRepository repo4 = new Repository("log4.txt");
        Controller controller4 = new Controller(repo4);
        controller4.addProgram(prog4);

        TextMenu menu = new TextMenu();
        menu.addCommand(new ExitCommand("0","exit"));
        menu.addCommand(new RunExample("1",ex1.toString(),controller1));
        menu.addCommand(new RunExample("2",ex2.toString(),controller2));
        menu.addCommand(new RunExample("3",ex3.toString(),controller3));
        menu.addCommand(new RunExample("4",ex4.toString(),controller4));
        menu.show();
    }
}
