package com.company.Model.ADTs;

import com.company.Model.Exceptions.MyException;

import java.util.HashMap;

public class MyDictionary<K,V> implements IDictionary {
    private HashMap<K,V> dictionary;

    public MyDictionary(){
        dictionary = new HashMap<K,V>();
    }

    @Override
    public String toString() {
        String s ="{";
        for (K key : dictionary.keySet())
            s+= key.toString() + " -> " + dictionary.get(key).toString() + ";";
        s+="}";
        return s;
    }

    @Override
    public Object lookup(Object key) throws MyException {
        if (!dictionary.containsKey((K) key))
            throw new MyException("Key doesn't exist.");
        return dictionary.get((K) key);
    }

    @Override
    public boolean isDefined(Object key) {
        return dictionary.containsKey((K) key);
    }

    @Override
    public void update(Object key, Object value) {
        dictionary.put((K) key, (V) value);
    }
}
