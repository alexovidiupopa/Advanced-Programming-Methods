package com.company.Model.Statements;

import com.company.Model.ADTs.IList;
import com.company.Model.Exceptions.MyException;
import com.company.Model.Expressions.Expression;
import com.company.Model.ProgramState.ProgramState;
import com.company.Model.Values.Value;

public class PrintStatement implements IStatement {

    Expression expression;

    public PrintStatement(Expression expression) {
        this.expression = expression;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        IList<Value> output = state.getOutput();
        output.add(expression.evaluate(state.getSymbolTable()));
        return null;
    }

    public String  toString(){
        return "print("+expression.toString()+")";
    }
}
