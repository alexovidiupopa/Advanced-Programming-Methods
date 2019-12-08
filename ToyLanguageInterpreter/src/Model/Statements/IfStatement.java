package Model.Statements;

import Model.ADTs.IDictionary;
import Model.Exceptions.MyException;
import Model.Expressions.Expression;
import Model.ProgramState.ProgramState;
import Model.Types.BoolType;
import Model.Types.Type;
import Model.Values.BoolValue;
import Model.Values.Value;

import java.io.IOException;

public class IfStatement implements IStatement {
    private Expression expression;
    private IStatement ifStatement, elseStatement;

    public IfStatement(Expression expression, IStatement ifStatement, IStatement elseStatement) {
        this.expression = expression;
        this.ifStatement = ifStatement;
        this.elseStatement = elseStatement;
    }

    public ProgramState execute(ProgramState state) throws MyException, IOException {
        Value result = this.expression.evaluate(state.getSymbolTable(),state.getHeap());
        if(((BoolValue) result).getValue()==true)
            this.ifStatement.execute(state);
        else
            this.elseStatement.execute(state);
        return null;
    }

    @Override
    public IDictionary<String, Type> typecheck(IDictionary<String, Type> typeEnv) throws MyException {
        Type typeExp = expression.typecheck(typeEnv);
        if (typeExp.equals(new BoolType())){
            IDictionary<String, Type> ifEnv, elseEnv;
            ifEnv = ifStatement.typecheck(typeEnv);
            elseEnv = elseStatement.typecheck(typeEnv);
            return typeEnv;
        }
        else
            throw new MyException("IF condition is not boolean");
    }

    @Override
    public String toString() {
        return "if " + this.expression.toString() + " then " + this.ifStatement.toString() + " else " + this.elseStatement.toString();
    }
}
