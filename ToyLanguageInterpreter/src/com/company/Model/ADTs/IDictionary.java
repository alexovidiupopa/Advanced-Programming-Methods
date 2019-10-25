package com.company.Model.ADTs;

public interface IDictionary<T,U> {

    U getValue(T key);
    boolean isDefined(T key);
    void update(T key, U value);
}
