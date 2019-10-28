package Model.Expressions;

import Model.ADTs.IDictionary;
import Model.Exceptions.MyException;
import Model.Values.Value;

public class VariableExpression implements Expression {
    String id;

    public VariableExpression(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return this.id;
    }

    @Override
    public Value evaluate(IDictionary<String, Value> table) throws MyException {
        return table.lookup(id);
    }
}
