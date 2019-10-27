package com.company.Model.ProgramState;

import com.company.Model.ADTs.IDictionary;
import com.company.Model.ADTs.IList;
import com.company.Model.ADTs.IStack;
import com.company.Model.Statements.IStatement;
import com.company.Model.Values.Value;

public class ProgramState {
    IStack<IStatement> executionStack;
    IDictionary<String, Value> symbolTable;
    IList<Value> output;
    IStatement originalProgram;

    public IStack<IStatement> getExecutionStack() {
        return executionStack;
    }

    @Override
    public String toString() {
        return "ProgramState:{" +
                "executionStack= " + executionStack.toString() +"\n" +
                "symbolTable= " + symbolTable.toString() + "\n" +
                "output= " + output.toString() +
                '}';
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

    public ProgramState(IStack<IStatement> executionStack, IDictionary<String, Value> symbolTable, IList<Value> output, IStatement originalProgram) {
        this.executionStack = executionStack;
        this.symbolTable = symbolTable;
        this.output = output;
        this.originalProgram = originalProgram;

    }
}
