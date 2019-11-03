package Controller;

import Model.ADTs.IStack;
import Model.Exceptions.MyException;
import Model.ProgramState.ProgramState;
import Model.Statements.IStatement;
import Repository.IRepository;

import java.io.IOException;

public class Controller {
    private IRepository repo;
    public Controller(IRepository repo) {
        this.repo = repo;
    }

    public ProgramState executeOneStep(ProgramState state) throws MyException, IOException {
        IStack<IStatement> stack = state.getExecutionStack();
        if (stack.isEmpty())
            throw new MyException("Empty execution stack.");
        IStatement currentStatement = stack.pop();
        return currentStatement.execute(state);
    }

    public void executeAllStep() throws MyException, IOException {
        ProgramState prog = this.repo.getCurrentProgram();
        this.repo.logProgramStateExecution();
        while(!prog.getExecutionStack().isEmpty()){
            this.executeOneStep(prog);
            this.repo.logProgramStateExecution();
        }
    }

    public IRepository getRepo(){return this.repo;}

    public void addProgram(ProgramState progState){this.repo.addProgram(progState);}
}
