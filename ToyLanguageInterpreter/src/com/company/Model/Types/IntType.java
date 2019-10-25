package com.company.Model.Types;

import com.sun.jdi.IntegerType;

public class IntType implements Type {
    public boolean equals(Object second){
        if(second instanceof IntType)
            return true;
        else
            return false;
    }
    public String toString(){
       return "int";
   }
}


