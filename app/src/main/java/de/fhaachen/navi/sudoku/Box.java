package de.fhaachen.navi.sudoku;

import java.util.List;

public class Box {
    List<Cell> values;

    public Box(List<Cell> values){
        this.values = values;
    }

    public Box(){

    }

    public void setValues(Cell cell){
        values.add(cell);
    }


}
