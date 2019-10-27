package com.company.Controller;

import com.company.Model.ADTs.IStack;
import com.company.Model.Exceptions.MyException;
import com.company.Model.ProgramState.ProgramState;
import com.company.Model.Statements.IStatement;
import com.company.Repository.IRepository;

public class Controller {
    private IRepository repo;

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

    public void executeAllStep() throws MyException{
        ProgramState prog = this.repo.getCurrentProgram();
        while(!prog.getExecutionStack().isEmpty()){
            System.out.println(prog);
            this.executeOneStep(prog);
        }
        System.out.println(prog);
    }
    public IRepository getRepo(){return this.repo;}
    public void addProgram(ProgramState progState){this.repo.addProgram(progState);}
}
