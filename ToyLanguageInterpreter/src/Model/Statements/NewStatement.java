package Model.Statements;

import Model.ADTs.IDictionary;
import Model.Exceptions.MyException;
import Model.Expressions.Expression;
import Model.ProgramState.ProgramState;
import Model.Types.ReferenceType;
import Model.Types.Type;
import Model.Values.ReferenceValue;
import Model.Values.Value;

import java.io.IOException;
import java.sql.Ref;

public class NewStatement implements IStatement{
    private String var_name;
    private Expression exp;

    public NewStatement(String var_name, Expression exp) {
        this.var_name = var_name;
        this.exp = exp;
    }

    @Override
    public String toString() {
        return "new(" + var_name + "," + exp.toString() + ")";
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException, IOException {
        if (state.getSymbolTable().isDefined(var_name)){
            Value value = state.getSymbolTable().lookup(var_name);
            if (value instanceof ReferenceValue){
                Value expressionValue = exp.evaluate(state.getSymbolTable(),state.getHeap());
                if(expressionValue.getType().equals(((ReferenceValue) value).getLocationType())){ // check if location type of value associated to var_name is the same as the expression evaluation type
                    int location = state.getHeap().allocate(expressionValue);
                    state.getSymbolTable().update(var_name,new ReferenceValue(location,expressionValue.getType()));
                }
                else throw new MyException("Types are not equal");
            }
            else
                throw new MyException("Value isn't of type ReferenceType");
        }
        else throw new MyException("Variable not defined.");
        return null;
    }

    @Override
    public IDictionary<String, Type> typecheck(IDictionary<String, Type> typeEnv) throws MyException {
        Type typeVar = typeEnv.lookup(var_name);
        Type typeExp = exp.typecheck(typeEnv);
        if(typeVar.equals(new ReferenceType(typeExp)))
            return typeEnv;
        else
            throw new MyException("New Statement - different types");
    }
}
