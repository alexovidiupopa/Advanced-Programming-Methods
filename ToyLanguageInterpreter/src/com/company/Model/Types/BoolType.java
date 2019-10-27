package com.company.Model.Types;

import com.company.Model.Values.BoolValue;

public class BoolType implements Type {
    public boolean equals(Object second){
        if (second instanceof BoolType)
            return true;
        else
            return false;
    }
    public String toString(){
        return "bool";
    }
    public BoolValue defaultValue(){return new BoolValue(false);}
}
