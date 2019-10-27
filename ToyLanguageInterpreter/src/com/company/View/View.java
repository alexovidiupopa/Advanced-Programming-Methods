package com.company.View;

import com.company.Controller.Controller;
import com.company.Model.ADTs.*;
import com.company.Model.Exceptions.MyException;
import com.company.Model.Expressions.ArithmeticExpression;
import com.company.Model.Expressions.ValueExpression;
import com.company.Model.Expressions.VariableExpression;
import com.company.Model.ProgramState.ProgramState;
import com.company.Model.Statements.*;
import com.company.Model.Types.BoolType;
import com.company.Model.Types.IntType;
import com.company.Model.Values.BoolValue;
import com.company.Model.Values.IntValue;
import com.company.Model.Values.Value;

import java.util.Scanner;

public class View {
    private Controller controller;
    private Scanner keyboard = new Scanner(System.in);
    private IStatement ex1= new CompoundStatement(new VariableDeclarationStatement("v",new IntType()), new CompoundStatement(new AssignmentStatement("v",new ValueExpression(new IntValue(2))), new PrintStatement(new VariableExpression("v"))));
    private IStatement ex2 = new CompoundStatement( new VariableDeclarationStatement("a",new IntType()),  new CompoundStatement(new VariableDeclarationStatement("b",new IntType()),
            new CompoundStatement(new AssignmentStatement("a", new ArithmeticExpression('+',new ValueExpression(new IntValue(2)),new ArithmeticExpression('*',new ValueExpression(new IntValue(3)),
                    new ValueExpression(new IntValue(5))))),  new CompoundStatement(new AssignmentStatement("b",new ArithmeticExpression('+',new VariableExpression("a"),
                    new ValueExpression(new IntValue(1)))), new PrintStatement(new VariableExpression("b"))))));
    private IStatement ex3 = new CompoundStatement(new VariableDeclarationStatement("a",new BoolType()), new CompoundStatement(new VariableDeclarationStatement("v", new IntType()),new CompoundStatement(new AssignmentStatement("a", new ValueExpression(new BoolValue(true)))
            , new CompoundStatement(new IfStatement(new VariableExpression("a"),new AssignmentStatement("v",new ValueExpression(new IntValue(2))), new AssignmentStatement("v", new ValueExpression(new IntValue(3)))), new PrintStatement(new VariableExpression("v"))))));

    /*private IStack<IStatement> exeStack = new MyStack<IStatement>();
    private IDictionary<String,Value> symbolTable = new MyDictionary<String,Value>();
    private IList<Value> output = new MyList<Value>();*/
    private ProgramState programState;
    public View(Controller controller) {
        this.controller = controller;
    }

    private void initProgram(IStatement statement){
        IStack<IStatement> exeStack = new MyStack<IStatement>();
        IDictionary<String,Value> symbolTable = new MyDictionary<String,Value>();
        IList<Value> output = new MyList<Value>();
        exeStack.push(statement);
        this.programState = new ProgramState(exeStack,symbolTable,output,statement);
        this.controller.addProgram(this.programState);
    }
    private void chooseProgram() throws MyException {
        System.out.println("Available programs:\n" +
                "Press: 1 for 'int v;v=2;print(v)'\n" +
                "2 for 'int a;int b; a=2+3*5;b=a+1;Print(b)'\n" +
                "3 for 'bool a; int v; a=true;(If a Then v=2 Else v=3);Print(v)'");
        int option;
        try {
            option = Integer.valueOf(this.keyboard.nextLine());
            switch (option) {
                case 1:
                    this.initProgram(ex1);
                    break;
                case 2:
                    this.initProgram(ex2);
                    break;
                case 3:
                    this.initProgram(ex3);
                    break;
                default:
                    throw new MyException("Wrong cmd.");
            }
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
        }
    }
    private void chooseExecMode() throws MyException{
        System.out.println("1 for one-step, 2 for all steps");
        int option;
        try {
            option = Integer.valueOf(this.keyboard.nextLine());
            switch (option) {
                case 1:
                    this.controller.executeOneStep(this.programState);
                    break;
                case 2:
                    this.controller.executeAllStep();
                    break;
                case 0:
                    throw new MyException("Exit");
                default:
                    throw new MyException("Wrong cmd.");
            }
        }
        catch(MyException e){
            throw e;
        }
    }

    public void run() {
            try {
                chooseProgram();
            } catch (MyException e) {
                System.out.println(e.getMessage());
                return;
            }
            while (true){
                try{
                    chooseExecMode();
                }
                catch (MyException e){
                    System.out.println(e.getMessage());
                    if (e.getMessage().equals("Exit"))
                        break;
                }
            }
        }


}
