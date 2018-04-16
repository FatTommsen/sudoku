package de.fhaachen.navi.sudoku;

import java.util.List;

public class Box {
    List<Integer> values;

    public Box(List<Integer> values){
        this.values = values;
    }

    public Box(){

    }

    public void setValues(int value){
        values.add(value);
    }

}
