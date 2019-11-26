package Model.Statements;

import Model.ADTs.IStack;
import Model.ProgramState.ProgramState;

public class CompoundStatement implements IStatement {
    private IStatement first, second;

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
        return null;
    }
    public String toString(){
        return "("+this.first.toString() + ";" + this.second.toString() +")";
    }
}
