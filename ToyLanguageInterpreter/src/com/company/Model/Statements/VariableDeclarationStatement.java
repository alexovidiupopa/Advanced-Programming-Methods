package com.company.Model.Statements;

import com.company.Model.ADTs.IDictionary;
import com.company.Model.Exceptions.MyException;
import com.company.Model.ProgramState.ProgramState;
import com.company.Model.Types.Type;
import com.company.Model.Values.Value;

public class VariableDeclarationStatement implements IStatement{
    String name;
    Type type;

    public VariableDeclarationStatement(String name, Type type) {
        this.name = name;
        this.type = type;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        IDictionary<String, Value> table = state.getSymbolTable();
        if (table.isDefined(this.name))
            throw new MyException("Variable already declared");
        else
            table.update(this.name, this.type.defaultValue());
        return state;
    }

    @Override
    public String toString() {
        return this.type.toString() + this.name;
    }
}
