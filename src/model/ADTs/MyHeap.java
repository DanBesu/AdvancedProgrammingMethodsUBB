package model.ADTs;

public class MyHeap<TKey, TValue> extends SymbolsDict<TKey, TValue>{
    int firstFreeLocation = 1;

    public MyHeap(){
        super();
    }

    private int setNextFreeLocation(){
        return this.firstFreeLocation + 1;
    }

    public int getFirstFreeLocation(){
        int copy = firstFreeLocation;
        firstFreeLocation = setNextFreeLocation();
        return copy;
    }
}
