package model.values;

import model.types.IType;
import model.types.ReferenceType;

public class ReferenceValue implements IValue {
    int heapAddress;
    IType locationType;

    public ReferenceValue(int heapAddress, IType locationType) {
        this.heapAddress = heapAddress;
        this.locationType = locationType;
    }

    public IType getReferenceType(){
        return locationType;
    }

    @Override
    public IType getType() {
        return new ReferenceType(locationType);
    }

    public boolean equals(Object another) {
        return (another instanceof ReferenceValue && ((ReferenceValue) another).getHeapAddress() == heapAddress);
    }

    public int getHeapAddress() {
        return heapAddress;
    }

    public String toString(){
        return "(" + heapAddress + ", " + locationType.toString() + ")";
    }
}
