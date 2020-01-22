package Model.Statements.Semaphore;

import Model.ADTs.IDictionary;
import Model.ADTs.IHeap;
import Model.ADTs.ISemaphoreTable;
import Model.Exceptions.MyException;
import Model.Expressions.Expression;
import Model.ProgramState.ProgramState;
import Model.Statements.IStatement;
import Model.Types.IntType;
import Model.Types.Type;
import Model.Values.IntValue;
import Model.Values.Value;
import javafx.util.Pair;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class ReleaseStatement implements IStatement {
    private Expression var;

    @Override
    public String toString() {
        return "release(" + var.toString() + ")";
    }

    public ReleaseStatement(Expression var) {
        this.var = var;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException, IOException {
        ReentrantLock lock = new ReentrantLock();
        lock.lock();
        IDictionary<String, Value> symTable = state.getSymbolTable();
        IHeap<Value> heap = state.getHeap();
        ISemaphoreTable semaphoreTable = state.getSemaphoreTable();
        if (symTable.isDefined(var.toString())) {
            int foundIndex = ((IntValue) symTable.lookup(var.toString())).getValue();
            if (semaphoreTable.isDefined(foundIndex)){
                Pair pair = (Pair) semaphoreTable.getSemaphoreTable().get(foundIndex);
                List<Integer> list = (List<Integer>) pair.getValue();
                int nr = (int) pair.getKey();
                if(list.contains(state.getId())){
                    list.remove(state.getId());
                }
            }
            else throw new MyException("Release-index not in sem table");
        }
        else throw new MyException("Release - variable not defined");
        lock.unlock();
        return null;
    }

    @Override
    public IDictionary<String, Type> typecheck(IDictionary<String, Type> typeEnv) throws MyException {
        Type typeExp = var.typecheck(typeEnv);
        if(typeExp.equals(new IntType()))
            return typeEnv;
        else throw new MyException("Release typechecker: var not type int");
    }
}
