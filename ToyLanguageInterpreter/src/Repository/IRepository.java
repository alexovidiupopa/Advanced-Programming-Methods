package Repository;

import Model.ProgramState.ProgramState;

public interface IRepository {
    public void addProgram(ProgramState progState);
    public ProgramState getCurrentProgram();
}
