package Model.Statements;

import Model.ADTs.IDictionary;
import Model.Exceptions.MyException;
import Model.Expressions.Expression;
import Model.Expressions.RelationalExpression;
import Model.ProgramState.ProgramState;
import Model.Types.Type;

import java.io.IOException;

public class SwitchStatement implements IStatement {
    private Expression exp,exp1,exp2;
    private IStatement statement1,statement2,statement3;

    @Override
    public String toString() {
        return "switch("+ exp.toString() + ")" +
                "(case "+ exp1.toString()  + " : " + statement1.toString() +
                ")(case " + exp2.toString() + " : " + statement2.toString() +
                ")(default " + statement3.toString() + ")";
    }

    public SwitchStatement(Expression exp, Expression exp1, Expression exp2, IStatement statement1, IStatement statement2, IStatement statement3) {
        this.exp = exp;
        this.exp1 = exp1;
        this.exp2 = exp2;
        this.statement1 = statement1;
        this.statement2 = statement2;
        this.statement3 = statement3;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException, IOException {
        IStatement newStatement = new IfStatement(new RelationalExpression(exp,exp1,"=="), statement1,new IfStatement(
                new RelationalExpression(exp,exp2,"=="),statement2,statement3
        ));
        state.getExecutionStack().push(newStatement);
        return null;
    }

    @Override
    public IDictionary<String, Type> typecheck(IDictionary<String, Type> typeEnv) throws MyException {
        Type typeExp = exp.typecheck(typeEnv);
        Type typeExp1 = exp1.typecheck(typeEnv);
        Type typeExp2 = exp2.typecheck(typeEnv);
        if (typeExp.equals(typeExp1) && typeExp.equals(typeExp2)) //check for same types
            return statement1.typecheck(statement2.typecheck(statement3.typecheck(typeEnv))); //typecheck the statements
        else
            throw new MyException("Incompatible types");
    }
}
