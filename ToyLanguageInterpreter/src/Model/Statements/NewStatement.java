package Model.Statements;

import Model.Exceptions.MyException;
import Model.Expressions.Expression;
import Model.ProgramState.ProgramState;
import Model.Types.ReferenceType;
import Model.Values.ReferenceValue;
import Model.Values.Value;

import java.io.IOException;

public class NewStatement implements IStatement{
    private String var_name;
    private Expression exp;

    public NewStatement(String var_name, Expression exp) {
        this.var_name = var_name;
        this.exp = exp;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException, IOException {
        if (state.getSymbolTable().isDefined(var_name)){
            Value value = state.getSymbolTable().lookup(var_name);
            if (value.getType().equals(new ReferenceType(null))){
                Value expressionValue = exp.evaluate(state.getSymbolTable());
                if(expressionValue.getType().equals(value.getType())){
                    int location = state.getHeap().allocate(expressionValue);
                    state.getSymbolTable().update(var_name,new ReferenceValue(location,expressionValue.getType()));
                }
                else throw new MyException("Types are not equal");
            }
            else
                throw new MyException("Value isn't of type ReferenceType");
        }
        else throw new MyException("Variable not defined.");
        return state;
    }
}
