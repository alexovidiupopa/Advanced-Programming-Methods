package Model.Expressions;

import Model.ADTs.IDictionary;
import Model.Exceptions.MyException;
import Model.Types.IntType;
import Model.Values.BoolValue;
import Model.Values.IntValue;
import Model.Values.Value;

public class RelationalExpression implements Expression {
    private Expression exp1,exp2;
    private String op;
    @Override
    public String toString() {
        return  this.exp1.toString() + this.op + this.exp2.toString();
    }

    public RelationalExpression(Expression exp1, Expression exp2,String op) {
        this.exp1 = exp1;
        this.exp2 = exp2;
        this.op = op;
    }

    @Override
    public Value evaluate(IDictionary<String, Value> table) throws MyException {
        Value value1, value2;
        value1 = exp1.evaluate(table);
        if (value1.getType().equals(new IntType())){
            value2 = exp2.evaluate(table);
            if(value2.getType().equals(new IntType())){
                IntValue toInteger1 = (IntValue)value1;
                IntValue toInteger2 = (IntValue)value2;
                int number1,number2;
                number1 = toInteger1.getValue();
                number2 = toInteger2.getValue();
                switch (op){
                    case ">=":
                        return new BoolValue(number1>=number2);
                    case ">":
                        return new BoolValue(number1>number2);
                    case "<=":
                        return new BoolValue(number1<=number2);
                    case "<":
                        return new BoolValue(number1<number2);
                    case "==":
                        return new BoolValue(number1==number2);
                    case "!=":
                        return new BoolValue(number1!=number2);
                    default:
                        throw new MyException("Invalid operand");
                }
            }
            else throw new MyException("Second operand is not an int.");
        }
        else throw new MyException("First operand is not an int.");
    }
}
