package Controller;

import Model.ADTs.IStack;
import Model.Exceptions.MyException;
import Model.ProgramState.ProgramState;
import Model.Statements.IStatement;
import Repository.IRepository;

public class Controller {
    private IRepository repo;
    private String programState;
    public Controller(IRepository repo) {
        this.repo = repo;
    }

    public ProgramState executeOneStep(ProgramState state) throws MyException {
        IStack<IStatement> stack = state.getExecutionStack();
        if (stack.isEmpty())
            throw new MyException("Empty execution stack.");
        IStatement currentStatement = stack.pop();
        return currentStatement.execute(state);
    }

    public String executeOneStepWrapper() throws MyException{
        ProgramState programState = this.repo.getCurrentProgram();
        String status="Program before one step execution:\n" + programState.toString();
        this.executeOneStep(programState);
        return status +"Program after one step execution:\n" + programState.toString();
    }
    public String executeAllStep() throws MyException{
        ProgramState prog = this.repo.getCurrentProgram();
        String programStates="";
        programStates+=prog.toString();
        while(!prog.getExecutionStack().isEmpty()){
            this.executeOneStep(prog);
            programStates+=prog.toString();
        }
        //programStates+=prog.toString();
        return programStates;
    }

    public IRepository getRepo(){return this.repo;}

    public void addProgram(ProgramState progState){this.repo.addProgram(progState);}
}
