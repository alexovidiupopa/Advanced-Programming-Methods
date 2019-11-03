package View;

import Controller.Controller;
import Model.ADTs.*;
import Model.Expressions.ArithmeticExpression;
import Model.Expressions.ValueExpression;
import Model.Expressions.VariableExpression;
import Model.ProgramState.ProgramState;
import Model.Statements.*;
import Model.Types.BoolType;
import Model.Types.IntType;
import Model.Values.BoolValue;
import Model.Values.IntValue;
import Model.Values.Value;
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


        IStack<IStatement> exeStack1 = new MyStack<IStatement>();
        IDictionary<String, Value> symbolTable1 = new MyDictionary<String,Value>();
        IList<Value> output1 = new MyList<Value>();
        ProgramState prog1 = new ProgramState(exeStack1,symbolTable1,output1,ex1);
        IRepository repo1 = new Repository("log1.txt");
        Controller controller1 = new Controller(repo1);
        controller1.addProgram(prog1);

        IStack<IStatement> exeStack2 = new MyStack<IStatement>();
        IDictionary<String, Value> symbolTable2 = new MyDictionary<String,Value>();
        IList<Value> output2 = new MyList<Value>();
        ProgramState prog2 = new ProgramState(exeStack2,symbolTable2,output2,ex2);
        IRepository repo2 = new Repository("log2.txt");
        Controller controller2 = new Controller(repo2);
        controller2.addProgram(prog2);

        IStack<IStatement> exeStack3 = new MyStack<IStatement>();
        IDictionary<String, Value> symbolTable3 = new MyDictionary<String,Value>();
        IList<Value> output3 = new MyList<Value>();
        ProgramState prog3 = new ProgramState(exeStack3,symbolTable3,output3,ex3);
        IRepository repo3 = new Repository("log3.txt");
        Controller controller3 = new Controller(repo3);
        controller3.addProgram(prog3);

        TextMenu menu = new TextMenu();
        menu.addCommand(new ExitCommand("0","exit"));
        menu.addCommand(new RunExample("1",ex1.toString(),controller1));
        menu.addCommand(new RunExample("2",ex2.toString(),controller2));
        menu.addCommand(new RunExample("3",ex3.toString(),controller3));
        menu.show();
    }
}
