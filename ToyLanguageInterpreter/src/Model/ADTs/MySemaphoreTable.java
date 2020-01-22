package Model.ADTs;

import java.util.HashMap;
import java.util.Map;

public class MySemaphoreTable<V> implements ISemaphoreTable<V> {
    private int address = 1;
    private HashMap<Integer,V> semaphoreTable;

    public MySemaphoreTable() {
        semaphoreTable = new HashMap<>();
    }

    @Override
    public Map getSemaphoreTable() {
        return semaphoreTable;
    }

    @Override
    public void put(int location, Object value) {
        semaphoreTable.put(location, (V) value);
    }

    @Override
    public int getLocation() {
        address++;
        return address-1;
    }

    @Override
    public boolean isDefined(int location) {
        return semaphoreTable.containsKey(location);
    }

    @Override
    public void setSemaphoreTable(Map newSemTable) {
        semaphoreTable= (HashMap<Integer, V>) newSemTable;
    }

    public String toString(){
        String s ="{";
        for (Integer key : semaphoreTable.keySet()){
            s+= key + "->" + semaphoreTable.get(key).toString() + ";";
        }
        return s+"}";
    }

}
