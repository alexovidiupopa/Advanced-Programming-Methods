package com.company.Model.Expressions;

import com.company.Model.ADTs.IDictionary;
import com.company.Model.Exceptions.MyException;
import com.company.Model.Types.IntType;
import com.company.Model.Values.IntValue;
import com.company.Model.Values.Value;

public class ArithmeticExpression implements Expression {
    private Expression exp1,exp2;
    private char op; // 1-+, 2--, 3-*, 4-/

    public ArithmeticExpression(char op,Expression exp1, Expression exp2) {
        this.exp1 = exp1;
        this.exp2 = exp2;
        this.op = op;
    }

    @Override
    public String toString() {
        return this.exp1.toString() + " " + this.op + " " + this.exp2.toString();
    }

    @Override
    public Value evaluate(IDictionary<String, Value> table) throws MyException{
        Value v1,v2;
        v1 = exp1.evaluate(table);
        if(v1.getType().equals(new IntType())){
            v2 = exp2.evaluate(table);
            if(v2.getType().equals(new IntType())){
                IntValue i1 = (IntValue)v1;
                IntValue i2 = (IntValue)v2;
                int n1,n2;
                n1 = i1.getValue();
                n2 = i2.getValue();
                switch (op) {
                    case '+':
                        return new IntValue(n1+n2);
                    case '-':
                        return new IntValue(n1-n2);
                    case '*':
                        return new IntValue(n1*n2);
                    case '/':
                        if (n2==0)
                            throw new MyException("Division by zero!");
                        return new IntValue(n1/n2);
                    default:
                        throw new MyException("Invalid operand");
                }
            }
            else
                throw new MyException("Second operand is not an int.");
        }
        else throw new MyException("First operand is not an int.");
    }
}
