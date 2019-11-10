package Model.Statements;

import Model.Exceptions.MyException;
import Model.ProgramState.ProgramState;

import java.io.IOException;

public class HeapWriteStatement implements IStatement {
    @Override
    public ProgramState execute(ProgramState state) throws MyException, IOException {
        return null;
    }
}
