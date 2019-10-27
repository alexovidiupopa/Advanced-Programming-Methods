package com.company.Model.ADTs;

import com.company.Model.Exceptions.MyException;

import java.util.Stack;

public class MyStack<T> implements IStack {
    private Stack<T> stack;

    public MyStack() {
        this.stack = new Stack<T>();
    }

    @Override
    public Object pop() throws MyException {
        return stack.pop();
    }

    @Override
    public String toString() {
        String s = "{";
        for (T el:this.stack) {
            s += el.toString()+"|";
        }
        s+="}";
        return s.toString();
    }

    @Override
    public void push(Object value) {
        stack.push((T) value);
    }

    @Override
    public boolean isEmpty() {
        return stack.empty();
    }
}
