package com.company.Model.Expressions;

import com.company.Model.ADTs.IDictionary;
import com.company.Model.Exceptions.MyException;
import com.company.Model.Values.Value;

public interface Expression {
    Value evaluate(IDictionary<String, Value> table) throws MyException;

    @Override
    String toString();
}
