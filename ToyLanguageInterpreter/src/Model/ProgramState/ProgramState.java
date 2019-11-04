package Model.ProgramState;

import Model.ADTs.*;
import Model.Statements.IStatement;
import Model.Values.Value;

import java.io.BufferedReader;

public class ProgramState {
    private IStack<IStatement> executionStack;
    private IDictionary<String, Value> symbolTable;
    private IList<Value> output;
    private IDictionary<String, BufferedReader> fileTable;

    public IDictionary<String, BufferedReader> getFileTable() {
        return fileTable;
    }

    public void setFileTable(IDictionary<String, BufferedReader> fileTable) {
        this.fileTable = fileTable;
    }
    private IStatement originalProgram;

    public IStack<IStatement> getExecutionStack() {
        return executionStack;
    }

    @Override
    public String toString() {
        return "ProgramState:\n" +
                "ExecutionStack\n" + executionStack.toString() +"\n" +
                "SymbolTable\n" + symbolTable.toString() + "\n" +
                "Output\n" + output.toString()  +
                "File table\n" + fileTable.toString() + "\n" ;
    }

    public void setExecutionStack(IStack<IStatement> executionStack) {
        this.executionStack = executionStack;
    }

    public IDictionary<String, Value> getSymbolTable() {
        return symbolTable;
    }

    public void setSymbolTable(IDictionary<String, Value> symbolTable) {
        this.symbolTable = symbolTable;
    }

    public IList<Value> getOutput() {
        return output;
    }

    public void setOutput(IList<Value> output) {
        this.output = output;
    }

    public ProgramState(IStack<IStatement> executionStack, IDictionary<String, Value> symbolTable, IList<Value> output, IDictionary<String,BufferedReader> fileTable,IStatement originalProgram) {
        this.executionStack = executionStack;
        this.symbolTable = symbolTable;
        this.output = output;
        this.fileTable = fileTable;
        //this.originalProgram = clone((Object)originalProgram);
        this.executionStack.push(originalProgram);
    }

    public ProgramState(IStatement originalProgram){
        this.executionStack = new MyStack<IStatement>();
        this.symbolTable = new MyDictionary<String,Value>();
        this.output = new MyList<Value>();
        this.fileTable = new MyDictionary<String,BufferedReader>();
        this.executionStack.push(originalProgram);
    }
}
