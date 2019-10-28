package Model.Expressions;

import Model.ADTs.IDictionary;
import Model.Exceptions.MyException;
import Model.Values.Value;

public class ValueExpression implements Expression {
    Value value;

    public ValueExpression(Value val) {
        this.value = val;
    }

    @Override
    public String toString() {
        return this.value.toString();
    }

    @Override
    public Value evaluate(IDictionary<String, Value> table) throws MyException {
        return value;
    }
}
