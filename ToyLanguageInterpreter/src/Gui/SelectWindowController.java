package Gui;

import Controller.Controller;
import Model.ADTs.MyDictionary;
import Model.Exceptions.MyException;
import Model.Expressions.*;
import Model.ProgramState.ProgramState;
import Model.Statements.*;
import Model.Statements.Semaphore.AcquireStatement;
import Model.Statements.Semaphore.CreateSemaphoreStatement;
import Model.Statements.Semaphore.ReleaseStatement;
import Model.Types.*;
import Model.Values.BoolValue;
import Model.Values.IntValue;
import Model.Values.StringValue;
import Repository.IRepository;
import Repository.Repository;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class SelectWindowController implements Initializable {
    @FXML
    private Button selectBttn;
    @FXML
    private ListView<IStatement> selectItemListView;

    private MainWindowController mainWindowController;

    public MainWindowController getMainWindowController() {
        return mainWindowController;
    }

    public void setMainWindowController(MainWindowController mainWindowController) {
        this.mainWindowController = mainWindowController;
    }

    @FXML
    private IStatement selectExample(ActionEvent actionEvent) {
        return selectItemListView.getItems().get(selectItemListView.getSelectionModel().getSelectedIndex());
    }

    private List<IStatement> initExamples(){
        List<IStatement> list = new ArrayList<>();
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
        IStatement ex5 = new CompoundStatement(
                new VariableDeclarationStatement("v",new IntType()),
                new CompoundStatement(
                        new AssignmentStatement("v",new ValueExpression(new IntValue(10))),
                        new WhileStatement(
                                new RelationalExpression(new VariableExpression("v"),new ValueExpression(new IntValue(0)),">"),
                                new CompoundStatement(new PrintStatement(new VariableExpression("v")),
                                        new AssignmentStatement( "v",new ArithmeticExpression('-',new VariableExpression("v"),new ValueExpression(new IntValue(1))))
                                )
                        )));
        IStatement ex6 = new CompoundStatement(
                new VariableDeclarationStatement("v",new ReferenceType(new IntType())),
                new CompoundStatement(
                        new NewStatement("v",new ValueExpression(new IntValue(20))),
                        new CompoundStatement(
                                new PrintStatement(new HeapReadExpression(new VariableExpression("v"))), new CompoundStatement(
                                new VariableDeclarationStatement("a",new ReferenceType(new ReferenceType(new  IntType()))), new CompoundStatement(
                                new NewStatement("a",new VariableExpression("v")),new CompoundStatement(
                                new NewStatement("v",new ValueExpression(new IntValue(30))),
                                new PrintStatement(new ArithmeticExpression('+' ,new HeapReadExpression(new HeapReadExpression( new VariableExpression("a"))),new ValueExpression(new IntValue(5))))))))));
        IStatement ex7 = new CompoundStatement(
                new VariableDeclarationStatement("v",new ReferenceType(new IntType())),
                new CompoundStatement(
                        new NewStatement("v",new ValueExpression(new IntValue(20))),
                        new CompoundStatement(
                                new PrintStatement(new HeapReadExpression(new VariableExpression("v"))), new CompoundStatement(
                                new VariableDeclarationStatement("a",new ReferenceType(new ReferenceType(new  IntType()))), new CompoundStatement(
                                new NewStatement("a",new VariableExpression("v")),new CompoundStatement(
                                new HeapWriteStatement("v",new ValueExpression(new IntValue(30))),
                                new PrintStatement(new ArithmeticExpression('+' ,new HeapReadExpression(new HeapReadExpression( new VariableExpression("a"))),new ValueExpression(new IntValue(5))))))))));
        IStatement forked = new CompoundStatement(new HeapWriteStatement("a",new ValueExpression(new IntValue(30))),
                new CompoundStatement(new AssignmentStatement("v",new ValueExpression(new IntValue(32))),
                        new CompoundStatement(new PrintStatement(new VariableExpression("v")),new PrintStatement(new HeapReadExpression(new VariableExpression("a"))))));
        IStatement ex8 = new CompoundStatement(new VariableDeclarationStatement("v", new IntType()),
                new CompoundStatement(new VariableDeclarationStatement("a",new ReferenceType(new IntType())),
                        new CompoundStatement(new AssignmentStatement("v",new ValueExpression(new IntValue(10))),
                                new CompoundStatement(new NewStatement("a",new ValueExpression(new IntValue(22))),
                                        new CompoundStatement(new ForkStatement(forked),new CompoundStatement(new PrintStatement(new VariableExpression("v")),new PrintStatement(new HeapReadExpression(new VariableExpression("a"))))))
                        )));
        IStatement ex9 = new CompoundStatement(new VariableDeclarationStatement("v", new IntType()),
                new CompoundStatement(new VariableDeclarationStatement("a",new ReferenceType(new IntType())),
                        new CompoundStatement(new AssignmentStatement("v",new ValueExpression(new StringValue("aa"))),
                                new CompoundStatement(new NewStatement("a",new ValueExpression(new IntValue(22))),
                                        new CompoundStatement(new ForkStatement(forked),new CompoundStatement(new PrintStatement(new VariableExpression("v")),new PrintStatement(new HeapReadExpression(new VariableExpression("a"))))))
                        )));
        IStatement ex10= new CompoundStatement(new VariableDeclarationStatement("v",new BoolType()), new CompoundStatement(new AssignmentStatement("v",new ValueExpression(new IntValue(2))), new PrintStatement(new VariableExpression("v"))));
        IStatement ex11= new CompoundStatement(new VariableDeclarationStatement("v",new IntType()), new CompoundStatement(new AssignmentStatement("v",new ValueExpression(new BoolValue(true))), new PrintStatement(new VariableExpression("v"))));
        IStatement ex12 = new CompoundStatement(new VariableDeclarationStatement("a", new IntType()),new CompoundStatement(new VariableDeclarationStatement("b",new IntType()),
                new CompoundStatement(new VariableDeclarationStatement("c",new IntType()),new CompoundStatement(new AssignmentStatement("a",new ValueExpression(new IntValue(1))),
                        new CompoundStatement(new AssignmentStatement("b",new ValueExpression(new IntValue(2))), new CompoundStatement(new AssignmentStatement("c",new ValueExpression(new IntValue(5))),
                                new CompoundStatement(new SwitchStatement(new ArithmeticExpression('*',new VariableExpression("a"),new ValueExpression(new IntValue(10))),new ArithmeticExpression('*', new VariableExpression("b"), new VariableExpression("c")),
                                        new ValueExpression(new IntValue(10)), new CompoundStatement(new PrintStatement(new VariableExpression("a")),new PrintStatement(new VariableExpression("b"))),
                                        new CompoundStatement(new PrintStatement(new ValueExpression(new IntValue(100))),new PrintStatement(new ValueExpression(new IntValue(200)))),new PrintStatement(new ValueExpression(new IntValue(300)))),new PrintStatement(new ValueExpression(new IntValue(300))))))))));
        IStatement ex13 = new CompoundStatement(new VariableDeclarationStatement("a", new IntType()),new CompoundStatement(new VariableDeclarationStatement("b",new IntType()),
                new CompoundStatement(new VariableDeclarationStatement("c",new IntType()),new CompoundStatement(new AssignmentStatement("a",new ValueExpression(new StringValue("hello"))),
                        new CompoundStatement(new AssignmentStatement("b",new ValueExpression(new IntValue(2))), new CompoundStatement(new AssignmentStatement("c",new ValueExpression(new IntValue(5))),
                                new CompoundStatement(new SwitchStatement(new ArithmeticExpression('*',new VariableExpression("a"),new ValueExpression(new IntValue(10))),new ArithmeticExpression('*', new VariableExpression("b"), new VariableExpression("c")),
                                        new ValueExpression(new IntValue(10)), new CompoundStatement(new PrintStatement(new VariableExpression("a")),new PrintStatement(new VariableExpression("b"))),
                                        new CompoundStatement(new PrintStatement(new ValueExpression(new IntValue(100))),new PrintStatement(new ValueExpression(new IntValue(200)))),new PrintStatement(new ValueExpression(new IntValue(300)))),new PrintStatement(new ValueExpression(new IntValue(300))))))))));

        IStatement forked1 = new CompoundStatement(new AcquireStatement(new VariableExpression("cnt")),
                new CompoundStatement(new HeapWriteStatement("v1",new ArithmeticExpression('*',new HeapReadExpression(new VariableExpression("v1")),new ValueExpression(new IntValue(10)))),
                        new CompoundStatement(new PrintStatement(new HeapReadExpression(new VariableExpression("v1"))), new ReleaseStatement(new VariableExpression("cnt")))));

        IStatement forked2 = new CompoundStatement(new AcquireStatement(new VariableExpression("cnt")),
                new CompoundStatement(new HeapWriteStatement("v1",new ArithmeticExpression('*',new HeapReadExpression(new VariableExpression("v1")),new ValueExpression(new IntValue(10)))),
                        new CompoundStatement(new HeapWriteStatement("v1", new ArithmeticExpression('*',new HeapReadExpression(new VariableExpression("v1")),new ValueExpression(new IntValue(2)))),
                                new CompoundStatement(new PrintStatement(new HeapReadExpression(new VariableExpression("v1"))),new ReleaseStatement(new VariableExpression("cnt"))))));
        IStatement ex14 = new CompoundStatement(new VariableDeclarationStatement("v1",new ReferenceType(new IntType())),
                new CompoundStatement(new VariableDeclarationStatement("cnt", new IntType()),
                        new CompoundStatement(new NewStatement("v1",new ValueExpression(new IntValue(1))),
                                new CompoundStatement(new CreateSemaphoreStatement(new VariableExpression("cnt"), new HeapReadExpression(new VariableExpression("v1"))),
                                        new CompoundStatement(new ForkStatement(forked1),
                                                new CompoundStatement(new ForkStatement(forked2),
                                                        new CompoundStatement(new AcquireStatement(new VariableExpression("cnt")),
                                                                new CompoundStatement(new PrintStatement(new ArithmeticExpression('-',new HeapReadExpression(new VariableExpression("v1")),new ValueExpression(new IntValue(1)))),
                                                                        new ReleaseStatement(new VariableExpression("cnt"))))))))));
        list.add(ex1);
        list.add(ex2);
        list.add(ex3);
        list.add(ex4);
        list.add(ex5);
        list.add(ex6);
        list.add(ex7);
        list.add(ex8);
        list.add(ex9);
        list.add(ex10);
        list.add(ex11);
        list.add(ex12);
        list.add(ex13);
        list.add(ex14);
        return list;
    }

    private void displayExamples(){
        List<IStatement> examples = initExamples();
        selectItemListView.setItems(FXCollections.observableArrayList(examples));
        selectItemListView.getSelectionModel().select(0);
        selectBttn.setOnAction(actionEvent -> {
            int index = selectItemListView.getSelectionModel().getSelectedIndex();
            IStatement selectedProgram = selectItemListView.getItems().get(index);
            index++;
            ProgramState programState = new ProgramState(selectedProgram);
            IRepository repository = new Repository("log" + index + ".txt");
            Controller controller = new Controller(repository);
            controller.addProgram(programState);
            try {
                selectedProgram.typecheck(new MyDictionary<String, Type>());
                mainWindowController.setController(controller);
            } catch (MyException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR,e.getMessage());
                alert.show();
            }
            //mainWindowController.setController(controller);
        });
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        displayExamples();
    }
}
