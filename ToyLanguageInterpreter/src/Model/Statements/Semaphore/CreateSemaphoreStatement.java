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
import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;

public class CreateSemaphoreStatement implements IStatement {
    private Expression var;
    private Expression exp1;
    @Override
    public String toString() {
        return "createSemaphore(" + var.toString() + "," + exp1.toString() + ")";
    }

    public CreateSemaphoreStatement(Expression var, Expression exp1) {
        this.var = var;
        this.exp1 = exp1;
    }


    @Override
    public ProgramState execute(ProgramState state) throws MyException, IOException {
        ReentrantLock lock = new ReentrantLock();
        lock.lock();
        IDictionary<String,Value> symTable = state.getSymbolTable();
        IHeap<Value> heap = state.getHeap();
        ISemaphoreTable semaphoreTable = state.getSemaphoreTable();
        Value expValue = exp1.evaluate(symTable,heap);
        if (expValue.getType().equals(new IntType())){
            int actualValue = ((IntValue)expValue).getValue();
            int location = semaphoreTable.getLocation();
            semaphoreTable.put(location,new Pair<>(actualValue,new ArrayList<>()));
            String varValue = var.toString();
            if (symTable.isDefined(varValue) && var.evaluate(symTable,heap).getType().equals(new IntType())){
                symTable.update(varValue,new IntValue(location));
            }
            else throw new MyException("Create semaphore: variable not defined/ not of type int");
            lock.unlock();
        }
        else throw new MyException("Create semaphore: wrong type of expression value");
        return null;
    }

    @Override
    public IDictionary<String, Type> typecheck(IDictionary<String, Type> typeEnv) throws MyException {
        Type typeVar = var.typecheck(typeEnv);
        if(typeVar.equals(new IntType()))
        {
            Type typeExp = exp1.typecheck(typeEnv);
            if (typeExp.equals(new IntType())){
                return typeEnv;
            }
            else throw new MyException("Create sem typechecker: exp not type int");
        }
        else throw new MyException("Create sem typechecker: var not type int");
    }
}
