package com.company.Repository;

import com.company.Model.ProgramState.ProgramState;

public interface IRepository {
    public void addProgram(ProgramState progState);
    public ProgramState getCurrentProgram();
}
