package com.company.Model.ADTs;

import com.company.Model.Exceptions.MyException;

public interface IStack<T> {
    T pop() throws MyException;
    void push(T value);
    boolean isEmpty();

    @Override
    String toString();
}
