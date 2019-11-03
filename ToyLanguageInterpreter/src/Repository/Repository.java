package Repository;

import Model.ProgramState.ProgramState;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Repository implements IRepository {
    private List<ProgramState> programStates;
    private int currentIndex;
    private String path;
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

    @Override
    public void logProgramStateExecution() throws IOException {
        PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(this.path,true)));
        writer.println(this.programStates.get(this.currentIndex).toString());
    }
}
