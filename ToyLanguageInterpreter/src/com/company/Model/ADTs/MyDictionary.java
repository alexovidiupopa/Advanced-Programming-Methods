package com.company.Model.ADTs;

import java.util.HashMap;

public class MyDictionary<T,U> implements IDictionary {
    HashMap<T,U> dictionary;

    @Override
    public Object getValue(Object key) {
        return dictionary.get((T) key);
    }

    @Override
    public boolean isDefined(Object key) {
        return dictionary.containsKey((T) key);
    }

    @Override
    public void update(Object key, Object value) {
        dictionary.put((T) key, (U) value);
    }
}
