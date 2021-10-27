package model.expressions;

import model.ADTs.IDict;
import model.exceptions.MyException;
import model.values.IValue;

public class ValueExpr {
    IValue e;
//    ...
    IValue eval(IDict<String, IValue> table) throws MyException{
        return e;
    }
}
