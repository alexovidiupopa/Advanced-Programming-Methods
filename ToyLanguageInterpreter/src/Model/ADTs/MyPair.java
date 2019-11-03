package Model.ADTs;

public class MyPair<T,U> implements IPair {
    private T first;
    public MyPair(T first, U second) {
        this.first = first;
        this.second = second;
    }

    private U second;
    @Override
    public T getFirst() {
        return this.first;
    }

    @Override
    public U getSecond() {
        return this.second;
    }
}
