package Model.Statements;

import Model.Exceptions.MyException;
import Model.Expressions.Expression;
import Model.ProgramState.ProgramState;
import Model.Types.ReferenceType;
import Model.Values.ReferenceValue;
import Model.Values.Value;

import java.io.IOException;

public class HeapWriteStatement implements IStatement {
    private String var_name;
    private Expression exp;

    public HeapWriteStatement(String var_name, Expression exp) {
        this.var_name = var_name;
        this.exp = exp;
    }
    @Override
    public String toString() {
        return "wH(" + var_name + "," + exp.toString() + ")";
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException, IOException {
        if (state.getSymbolTable().isDefined(this.var_name)){
            Value val = state.getSymbolTable().lookup(this.var_name);
            if (val instanceof ReferenceValue){
                int address = ((ReferenceValue)val).getAddress();
                if (state.getHeap().get(address)!=null){ // check if there's anything at that address
                    Value evaluationValue = this.exp.evaluate(state.getSymbolTable(),state.getHeap());
                    if (evaluationValue.getType().equals(((ReferenceValue) val).getLocationType())){ //check if the types are equal and update the value at that address in the heap
                        state.getHeap().put(address,evaluationValue);
                    }
                    else
                        throw new MyException("Incompatible types.");
                }
                else
                    throw new MyException("Address is not a key in the heap.");
            }
            else
                throw new MyException("Value not of type Reference type");
        }
        else
            throw new MyException("Variable not defined.");
        return null;
    }
}
