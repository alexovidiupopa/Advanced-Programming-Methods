package View;

import Controller.Controller;
import Model.ADTs.MyDictionary;
import Model.Exceptions.MyException;
import Model.Types.Type;

import java.io.IOException;

public class RunExample extends Command {
    private Controller controller;
    public RunExample(String key, String description,Controller controller) {
        super(key, description);
        this.controller=controller;
    }

    @Override
    public void execute() {

        try {
            this.controller.getRepo().getProgramList().get(0).getExecutionStack().getValues().get(0).typecheck(new MyDictionary<String, Type>());
            this.controller.executeAllStep();
        } catch (InterruptedException | MyException e) {
            System.out.println(e.getMessage());;
        }

    }
}
