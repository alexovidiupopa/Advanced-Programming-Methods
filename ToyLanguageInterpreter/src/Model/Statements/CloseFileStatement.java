package Model.Statements;

import Model.ADTs.IDictionary;
import Model.Exceptions.MyException;
import Model.Expressions.Expression;
import Model.ProgramState.ProgramState;
import Model.Types.StringType;
import Model.Types.Type;
import Model.Values.StringValue;
import Model.Values.Value;

import java.io.BufferedReader;
import java.io.IOException;

public class CloseFileStatement implements IStatement {
    private Expression expression;

    @Override
    public String toString() {
        return "close(" + expression.toString() + ")";
    }

    public CloseFileStatement(Expression expression) {
        this.expression = expression;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException, IOException {
        Value evaluationValue;
        evaluationValue = this.expression.evaluate(state.getSymbolTable(),state.getHeap());
        if (evaluationValue.getType().equals(new StringType())) {
            StringValue downcastedValue = (StringValue) evaluationValue;
            String expressionValue = downcastedValue.getValue();
            if (state.getFileTable().isDefined(expressionValue)){
                BufferedReader associatedFileDescriptor = state.getFileTable().lookup(expressionValue);
                associatedFileDescriptor.close();
                state.getFileTable().delete(expressionValue);
            }
            else
                throw new MyException("Filename doesn't exist!");
        }
        else
            throw new MyException("Expression doesn't evaluate to a string.");
        return null;
    }

    @Override
    public IDictionary<String, Type> typecheck(IDictionary<String, Type> typeEnv) throws MyException {
        Type ExpressionType = expression.typecheck(typeEnv);
        if (ExpressionType.equals(new StringType())) {
            return typeEnv;
        }
        else
            throw new MyException("Close Statement -  expression type is not a string");
    }
}
