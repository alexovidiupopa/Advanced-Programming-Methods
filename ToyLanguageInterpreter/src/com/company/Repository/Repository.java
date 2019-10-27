package com.company.Repository;

import com.company.Model.ProgramState.ProgramState;

import java.util.ArrayList;
import java.util.List;

public class Repository implements IRepository {
    private List<ProgramState> programStates;
    private int currentIndex;

    public Repository() {
        this.programStates = new ArrayList<>();
        this.currentIndex=0;
    }

    @Override
    public void addProgram(ProgramState progState) {
        this.programStates.add(progState);
    }

    @Override
    public ProgramState getCurrentProgram() {
        return this.programStates.get(this.currentIndex);
    }
}
