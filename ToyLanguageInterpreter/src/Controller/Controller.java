package Controller;

import Model.ADTs.IStack;
import Model.Exceptions.MyException;
import Model.ProgramState.ProgramState;
import Model.Statements.IStatement;
import Model.Values.ReferenceValue;
import Model.Values.Value;
import Repository.IRepository;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
            prog.getHeap().setContent(safeGarbageCollector
                    (getAddrFromSymTable(prog.getSymbolTable().getContent().values(),
                            prog.getHeap().getContent().values()),prog.getHeap().getContent()));
            this.repo.logProgramStateExecution();
            }
    }

    public IRepository getRepo(){return this.repo;}

    public void addProgram(ProgramState progState){this.repo.addProgram(progState);}

    Map<Integer, Value> safeGarbageCollector(List<Integer> addressesFromSymbolTable, Map<Integer,Value> heap)
    {
        return heap.entrySet()
                .stream()
                .filter(e->addressesFromSymbolTable.contains(e.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    List<Integer> getAddrFromSymTable(Collection<Value> symTableValues, Collection<Value> heap){
        return  Stream.concat(
                heap.stream()
                        .filter(v-> v instanceof ReferenceValue)
                        .map(v-> {ReferenceValue v1 = (ReferenceValue)v; return v1.getAddress();}),
                symTableValues.stream()
                        .filter(v-> v instanceof ReferenceValue)
                        .map(v-> {ReferenceValue v1 = (ReferenceValue)v; return v1.getAddress();})
                )
                .collect(Collectors.toList());
    }
}
