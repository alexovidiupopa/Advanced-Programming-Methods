package com.company.Model.ADTs;

import java.util.Stack;

public class MyStack<T> implements IStack {
    Stack<T> stack;
    @Override
    public Object pop() {
        return stack.pop();
    }

    @Override
    public void push(Object value) {
        stack.push((T) value);
    }

    @Override
    public Object top() {
        return stack.peek();
    }

    @Override
    public boolean isEmpty() {
        return stack.empty();
    }
}
