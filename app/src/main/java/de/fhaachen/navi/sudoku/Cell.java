package de.fhaachen.navi.sudoku;

import java.util.List;


public class Cell {
    private int value;
    private List<Integer> possibleValues;
    boolean bFromBeginning = false;
    boolean bVisible = true;

    public Cell(int value){
        this.value = value;
    }

    public Cell(){

    }

    public Cell(int value, List<Integer> possibleValues) {
        this.value = value;
        this.possibleValues = possibleValues;
    }

//    public boolean isMoveAvaliable(int value){
//        for(int i = 0; i < 3; i++){
//            for (int j = 0; j < 3; j++){
//                if(box.getValue(i, j) != value){
//                    return false;
//                }
//            }
//        }
//        return possibleValues.contains(value);
//    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getValue(){
        return value;
    }

    public void setPossibleValues(List<Integer> possibleValues) {
        this.possibleValues = possibleValues;
    }

    public boolean isVisible(){
        return bVisible;
    }

    public void setVisible( boolean bValue ){
        bVisible = bValue;
    }

    public void setFromBeginning(boolean bValue){
        bFromBeginning = bValue;
    }

    public boolean isFromBeginning(){
        return bFromBeginning;
    }

}
