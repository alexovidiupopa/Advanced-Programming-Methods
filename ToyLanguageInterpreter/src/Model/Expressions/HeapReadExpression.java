package Model.Expressions;

import Model.ADTs.IDictionary;
import Model.Exceptions.MyException;
import Model.Values.Value;

public class HeapReadExpression implements Expression {
    @Override
    public Value evaluate(IDictionary<String, Value> table) throws MyException {
        return null;
    }
}
