package com.company.Model.Statements;

import com.company.Model.ProgramState.ProgramState;

public class NopStatement implements IStatement{
    @Override
    public ProgramState execute(ProgramState state) {
        return null;
    }
}
