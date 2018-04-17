package de.fhaachen.navi.sudoku;

public class Solver {
    private Field board;

    public Solver( Field board){
        this.board = board;
    }
    public boolean backtrack(){
        Cell c = board.findFirstUnsolved();
        if( c == null ) {
            return true;
        }
        for( int i = 1; i <= 9; i++ ){
            c.setValue(i);
            if ( board.isValid(true)){
                if( backtrack() ){
                    return true;
                }
            }
        }
        c.setValue(0);
        return false;
    }

}
