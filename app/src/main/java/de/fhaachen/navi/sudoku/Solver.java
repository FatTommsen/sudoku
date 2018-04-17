package de.fhaachen.navi.sudoku;

public class Solver {
    private Field board;
    private int iSolutionAmount = 0;

    public Solver( Field board){
        this.board = board;
    }
    public boolean backtrack(){
        return backtrack( false );
    }

    private boolean backtrack(boolean bCount){
        Cell c = board.findFirstUnsolved();
        if( c == null ) {
            if( bCount ){
                if( iSolutionAmount != 0 ){
                    throw new IllegalArgumentException("");
                }
                iSolutionAmount ++;
                return false;
            }
            else{
                return true;
            }
        }
        for( int i = 1; i <= 9; i++ ){
            c.setValue(i);
            if ( board.isValid(true)){
                if( backtrack( bCount ) ){
                    return true;
                }
            }
        }
        c.setValue(0);
        return false;
    }

    public boolean countSolutions(){
        try {
            backtrack(true);
        }
        catch( IllegalArgumentException e){
            return false;
        }
        return true;
    }
}
