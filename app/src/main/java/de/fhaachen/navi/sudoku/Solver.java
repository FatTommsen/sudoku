package de.fhaachen.navi.sudoku;

public class Solver {
    private Field board;
    // mode 0 = gib die erste gefundene Lösung zurück.
    // mode 1 = gib zurück ob das Feld eindeutig lösbar ist
    // mode 2 = es wurde schon eine Lösung gefunden
    // mode 3 = exit in Ausgangszustand
    private int mode = 0;
    private boolean bFound = false;

    public Solver( Field board){
        mode = 0;
        this.board = board;
    }


    public boolean backtrack(){
        Cell c = board.findFirstUnsolved();
        if( c == null ) {
            if( mode == 1 ){
                mode = 2;
                return false;
            }
            else if( mode == 2 ){
                mode = 3;
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
                else if( mode == 3 ){
                    c.setValue(0);
                    return false;
                }
            }
        }
        c.setValue(0);
        return false;
    }

    public boolean isUnique(){
        mode = 1;
        backtrack();
        if( mode == 2 ){
            return true;
        }
        return false;
    }
}