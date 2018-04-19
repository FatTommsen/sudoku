package de.fhaachen.navi.sudoku;

public class Solver {
    private Field board;
    private Mode mode;
    private boolean bFound = false;
    public enum Mode{
        FindFirstSolution,
        UniqueTest,
        UniqueOneFound,
        UniqueAbort
    }

    public Solver(Field board){
        mode = Mode.FindFirstSolution;
        this.board = board;
    }

    public boolean solve(){
        mode = Mode.FindFirstSolution;
        return backtrack();
    }


    private boolean backtrack(){
        Cell c = board.findFirstUnsolved();
        if( c == null ) {
            if( mode == Mode.UniqueTest ){
                mode = Mode.UniqueOneFound;
                return false;
            }
            else if( mode == Mode.UniqueOneFound ){
                mode = Mode.UniqueAbort;
                return false;
            }
            else{
                return true;
            }
        }
        for( int i = 1; i <= 9; i++ ){
            c.setValue(i);
            if ( board.isValid(true)){
                if( backtrack() ){
                    return true;
                }
                else if( mode == Mode.UniqueAbort){
                    c.setValue(0);
                    return false;
                }
            }
        }
        c.setValue(0);
        return false;
    }

    public boolean isUnique(){
        mode = Mode.UniqueTest;
        backtrack();
        if( mode == Mode.UniqueOneFound ){
            return true;
        }
        return false;
    }

    public Mode analyse(){
        mode = Mode.UniqueTest;
        backtrack();
        return mode;
    }
}