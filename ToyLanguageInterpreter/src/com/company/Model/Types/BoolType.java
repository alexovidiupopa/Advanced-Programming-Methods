package com.company.Model.Types;

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
}
