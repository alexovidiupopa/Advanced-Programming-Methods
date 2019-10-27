package com.company.Model.Statements;

import com.company.Model.Exceptions.MyException;
import com.company.Model.Expressions.Expression;
import com.company.Model.ProgramState.ProgramState;
import com.company.Model.Types.BoolType;
import com.company.Model.Values.BoolValue;
import com.company.Model.Values.Value;

public class IfStatement implements IStatement {
    Expression expression;
    IStatement statement1, statement2;

    public IfStatement(Expression expression, IStatement statement1, IStatement statement2) {
        this.expression = expression;
        this.statement1 = statement1;
        this.statement2 = statement2;
    }

    public ProgramState execute(ProgramState state) throws MyException {
        Value result = this.expression.evaluate(state.getSymbolTable());
        //result = (BoolValue)result;
        if(((BoolValue) result).getValue()==true)
            this.statement1.execute(state);
        else
            this.statement2.execute(state);
        return null;
    }

    @Override
    public String toString() {
        return "if " + this.expression.toString() + " then" + this.statement1.toString() + " else " + this.statement2.toString();
    }
}
