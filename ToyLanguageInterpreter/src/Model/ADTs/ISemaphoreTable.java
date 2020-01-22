package Model.ADTs;

import java.util.Map;

public interface ISemaphoreTable<V> {
    String toString();
    Map<Integer,V> getSemaphoreTable();
    void setSemaphoreTable(Map<Integer,V> newSemTable);
    void put(int location, V value);
    int getLocation();
    boolean isDefined(int location);
}
