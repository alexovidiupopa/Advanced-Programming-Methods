package com.company.Model.Expressions;

import com.company.Model.ADTs.IDictionary;
import com.company.Model.Exceptions.MyException;
import com.company.Model.Types.BoolType;
import com.company.Model.Values.BoolValue;
import com.company.Model.Values.Value;

public class BooleanExpression implements Expression {
    Expression exp1,exp2;
    String op;

    @Override
    public Value evaluate(IDictionary<String, Value> table) throws MyException {
        Value v1,v2;
        v1 = exp1.evaluate(table);
        if (v1.getType().equals(new BoolType())){
            v2 = exp2.evaluate(table);
            if (v2.getType().equals(new BoolType())){
                BoolValue i1 = (BoolValue)v1;
                BoolValue i2 = (BoolValue)v2;
                boolean n1,n2;
                n1 = i1.getValue();
                n2 = i2.getValue();
                if (op=="and"){
                    return new BoolValue(n1 && n2);
                }
                if(op=="or"){
                    return new BoolValue(n1 || n2);
                }
                else throw new MyException("Invalid operand");
            }
            else
                throw new MyException("Second operand is not a bool.");
        }
        else
            throw new MyException("First second operand is not a bool.");
    }

    @Override
    public String toString() {
        return this.exp1.toString() + " " + this.op + " " + this.exp2.toString();
    }

    public BooleanExpression(Expression exp1, Expression exp2, String op) {
        this.exp1 = exp1;
        this.exp2 = exp2;
        this.op = op;
    }
}
