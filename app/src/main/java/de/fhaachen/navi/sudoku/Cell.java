package de.fhaachen.navi.sudoku;

import java.util.List;


public class Cell {
    private int value;
    private List<Integer> possibleValues;
    private Box box;

    public Cell(int value, Box box){
        this.value = value;
        this.box = box;
    }

    public Cell(){

    }

    public Cell(int value, List<Integer> possibleValues) {
        this.value = value;
        this.possibleValues = possibleValues;
    }

    public boolean isMoveAvaliable(int value){
        for(int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                if(box.getValue(i, j) != value){
                    return false;
                }
            }
        }
        return possibleValues.contains(value);

    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getValue(){
        return value;
    }

    public void setPossibleValues(List<Integer> possibleValues) {
        this.possibleValues = possibleValues;
    }
}
