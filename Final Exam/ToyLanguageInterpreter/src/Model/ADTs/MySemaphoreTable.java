package Model.ADTs;

import Model.Values.IntValue;
import javafx.util.Pair;

import java.util.List;
import java.util.Map;

public class MySemaphoreTable implements ISemaphoreTable {
    private int addr = 1;
    private IDictionary<Integer, ITuple> table;
    public MySemaphoreTable() {
        table = new MyDictionary<>();
    }


    @Override
    public void setSemaphoreTable(IDictionary<Integer, ITuple> sem) {
        table = sem;
    }

    @Override
    public IDictionary<Integer,ITuple > getSemaphoreTable() {
        return table;
    }

    @Override
    public int getSemaphoreAddress() {
        addr*=10;
        return addr/10;
    }

    @Override
    public void put(int index, ITuple listPair) {
        table.update(index,listPair);
    }

    @Override
    public String toString() {
        String s="";
        for(ITuple entry: table.getContent().values()){
            s+="(";
            s+=entry.getFirst();
            s+=",{";
            for(Integer intg : entry.getSecond())
                s+=intg+" ";
            s+="},";
            s+=entry.getThird();
            s+=")";
            s+="\n";
        }
        return s;
    }
}
