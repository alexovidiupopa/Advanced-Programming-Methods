package Repository;

import Model.ProgramState.ProgramState;

import java.io.IOException;

public interface IRepository {
    public void addProgram(ProgramState progState);
    public ProgramState getCurrentProgram();
    public void logProgramStateExecution() throws IOException;
}
