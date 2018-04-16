package de.fhaachen.navi.sudoku;

import java.util.List;

public class Field {
    private int DEFAULT_SIZE = 9;
    public Cell[][] board;
    List<Box> boxes;


    public Field(Cell[][] board){
        this.board = board;
        for(int i = 0; i < 9; i++){
            boxes.add(new Box(new int[3][3]));
        }
    }

    public Field(){
        board = new Cell[DEFAULT_SIZE][DEFAULT_SIZE];
    }

    private int getCellValue(int x, int y){
        return board[x][y].getValue();
    }
}
