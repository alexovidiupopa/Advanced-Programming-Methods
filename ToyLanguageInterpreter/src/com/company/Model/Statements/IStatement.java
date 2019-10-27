package com.company.Model.Statements;

import com.company.Model.Exceptions.MyException;
import com.company.Model.ProgramState.ProgramState;

public interface IStatement {
    ProgramState execute(ProgramState state) throws MyException;
}
