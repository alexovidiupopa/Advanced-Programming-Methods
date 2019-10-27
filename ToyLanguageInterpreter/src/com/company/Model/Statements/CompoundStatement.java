package com.company.Model.Statements;

import com.company.Model.ADTs.IStack;
import com.company.Model.ProgramState.ProgramState;

public class CompoundStatement implements IStatement {
    IStatement first, second;

    public CompoundStatement(IStatement first, IStatement second) {
        this.first = first;
        this.second = second;
    }

    public IStatement getFirst() {
        return first;
    }

    public IStatement getSecond() {
        return second;
    }

    @Override
    public ProgramState execute(ProgramState state) {
        IStack<IStatement> stack = state.getExecutionStack();
        stack.push(second);
        stack.push(first);
        return state;
    }
    public String toString(){
        return "("+this.first.toString() + ";" + this.second.toString() +")";
    }
}
