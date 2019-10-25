package com.company.Model.Values;

import com.company.Model.Types.IntType;
import com.company.Model.Types.Type;

public class IntValue implements Value {

    private int val;
    IntValue(int val){
        this.val = val;
    }
    public int getValue(){
        return this.val;
    }
    public String toString(){
        return String.valueOf(this.val);
    }
    public Type getType(){
        return new IntType();
    }
}
