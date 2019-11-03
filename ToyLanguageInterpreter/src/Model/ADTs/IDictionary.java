package Model.ADTs;

import Model.Exceptions.MyException;

public interface IDictionary<K,V> {

    public V lookup(K key) throws MyException;
    boolean isDefined(K key);
    void update(K key, V value);
    void delete(K key) throws MyException;
    @Override
    String toString();
}
