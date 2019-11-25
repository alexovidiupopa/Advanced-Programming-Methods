package Model.Statements;

import Model.Exceptions.MyException;
import Model.ProgramState.ProgramState;

import java.io.IOException;

public class ForkStatement implements IStatement {
    public IStatement getStatement() {
        return statement;
    }

    public void setStatement(IStatement statement) {
        this.statement = statement;
    }

    private IStatement statement;
    @Override
    public ProgramState execute(ProgramState state) throws MyException, IOException {
        return null;
    }
}
