package com.company.Model.Expressions;

import com.company.Model.ADTs.IDictionary;
import com.company.Model.Exceptions.MyException;
import com.company.Model.Values.IntValue;
import com.company.Model.Values.Value;

public class ValueExpression implements Expression {
    Value val;

    public ValueExpression(Value val) {
        this.val = val;
    }

    @Override
    public String toString() {
        return this.val.toString();
    }

    @Override
    public Value evaluate(IDictionary<String, Value> table) throws MyException {
        return val;
    }
}
