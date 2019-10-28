package Model.Expressions;

import Model.ADTs.IDictionary;
import Model.Exceptions.MyException;
import Model.Values.Value;

public interface Expression {
    Value evaluate(IDictionary<String, Value> table) throws MyException;

    @Override
    String toString();
}
