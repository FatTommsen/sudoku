package de.fhaachen.navi.sudoku;


public class Field {
    private final int DEFAULT_SIZE = 9;
    private Cell[][] board;
    private Box[][] boxes;


    public Field(){
        board = new Cell[DEFAULT_SIZE][DEFAULT_SIZE];
        for(int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++) {
                boxes[i][j] = new Box();
            }
        }
        for(int i = 0; i < DEFAULT_SIZE; i++){
            for (int j = 0; j < DEFAULT_SIZE; j++){
                Cell cell = new Cell();
                board[i][j] = cell;
                boxes[i / 3][j / 3].setValues(cell);
            }
        }
    }

    public void setCell(int x, int y, Cell cell){
        board[x][y] = cell;
    }
}
