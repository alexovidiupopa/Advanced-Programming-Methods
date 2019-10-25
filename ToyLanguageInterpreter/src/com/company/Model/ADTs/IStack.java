package com.company.Model.ADTs;

public interface IStack<T> {
    T pop();
    void push(T value);
    T top();
    boolean isEmpty();
}
