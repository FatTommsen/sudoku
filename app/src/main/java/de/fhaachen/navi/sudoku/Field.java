package de.fhaachen.navi.sudoku;


public class Field {
    private final int DEFAULT_SIZE = 9;
    private Cell[][] board;
    private Box[][] boxes;


    public Field(){
        board = new Cell[DEFAULT_SIZE][DEFAULT_SIZE];
        boxes = new Box[3][3];
        for(int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++) {
                boxes[i][j] = new Box();
            }
        }
        for(int i = 0; i < DEFAULT_SIZE; i++){
            for (int j = 0; j < DEFAULT_SIZE; j++){
                Cell cell = new Cell(0);
                board[j][i] = cell;
                boxes[j / 3][i / 3].setValues(cell);
            }
        }
    }

    public void setCell(int x, int y, Cell cell){
        board[x][y] = cell;
    }

    public void setValue( int x, int y, int value ){
        board[x][y].setValue( value );
    }

    public Cell getCell( int x, int y){
        return board[x][y];
    }

    public int getValue( int x, int y){ return board[x][y].getValue();}

    public Cell findFirstUnsolved(){
        for( Cell[] row: board){
            for( Cell c : row ){
                if( c.getValue() == 0 ){
                    return c;
                }
            }
        }
        return null;
    }

    private boolean checkRow( boolean bInProgress, Cell[] cells ){
        boolean[] bValues = new boolean[9];
        for( Cell c : cells ){
            if( c.getValue() != 0 ){
                if( !bValues[c.getValue() -1 ] ){
                    bValues[ c.getValue() - 1 ] = true;
                }
                else return false;
            }
            else if ( !bInProgress ){
                return false;
            }
        }
        return true;
    }

    public boolean isColValid( boolean bInProgress ){
        for( Cell[] col: board ) {
            if( !checkRow( bInProgress, col )){
                return false;
            }
        }
        return true;
    }

    public boolean isRowValid( boolean bInProgress ){
        for( int y = 0; y < board[0].length; y++ ){
            Cell[] row = new Cell[9];
            for( int x = 0; x < 9; x++ ){
                row[x] = board[x][y];
            }
            if( !checkRow( bInProgress,row )){
                return false;
            }
        }
        return true;
    }

    public boolean isBoxValid( boolean bInProgress ){
        for( Box[] row : boxes ){
            for( Box b : row ){
                if( !b.isValid(bInProgress)){
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isValid(boolean bInProgress){
        if( !isColValid(bInProgress)){
            return false;
        }
        if( !isRowValid(bInProgress)){
            return false;
        }
        if( !isBoxValid(bInProgress)){
            return false;
        }
        return true;
    }

    public void setAllCellsInvisble(){
        for( Cell[] row: board){
            for( Cell c : row ){
                c.setVisible(false);
            }
        }
    }

    public String toString(){
        StringBuilder strBuild = new StringBuilder();
        String strMaxBoundary = "=========================================\n";
        String strMinBoundary = "-----------------------------------------\n";
        strBuild.append( strMaxBoundary );
        for( int y = 0; y < 9; y ++ ){
            strBuild.append("||");
            for( int x = 0; x < 9; x++ ){
                strBuild.append(" ");
                if( board[x][y].getValue() == 0 ){
                    strBuild.append(" ");
                }
                else {
                    strBuild.append(board[x][y].getValue());
                }
                strBuild.append( " ");
                if( (x+1) % 3 == 0 ){
                    strBuild.append("||");
                }
                else strBuild.append("|");
            }
            strBuild.append("\n");
            if( (y+1) % 3 == 0 ){
                strBuild.append(strMaxBoundary);
            }
            else{
                strBuild.append(strMinBoundary);
            }
        }
        return strBuild.toString();
    }

    public Field clone(){
        Field f = new Field();
        for( int x = 0; x < 9; x++ ){
            for( int y = 0; y < 9; y ++ ){
                f.setValue( x, y, board[x][y].getValue());
            }
        }
        return f;
    }

    public Cell getCellFromBox( int box, int number){
        return boxes[ box / 3 ][box % 3 ].getCell(number);
    }
}