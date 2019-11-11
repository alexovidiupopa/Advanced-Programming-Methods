package Model.ADTs;

public interface IHeap<T> {
    int allocate(T val);
    T get(int addr);
    void put(int addr, T val);
    T deallocate(int addr);

}
