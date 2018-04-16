package de.fhaachen.navi.sudoku;


public class Field {
    private final int DEFAULT_SIZE = 9;
    private Cell[][] board;
    private Box[][] boxes;


    public Field(){
        if(board[0].length != DEFAULT_SIZE){
            throw new NumberFormatException();
        }
        for(int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++) {
                boxes[i][j] = new Box();
            }
        }
        for(int i = 0; i < 9; i++){
            for (int j = 0; j < 9; j++){
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
