package model.types;

public class IntType implements IType{

    public boolean equals(Object another){
        return another instanceof IntType;
    }

    public String toString(){
        return "int";
    }
}
