package de.fhaachen.navi.sudoku;

import java.util.ArrayList;
import java.util.Random;

public class Generator {
    Field field = new Field();
    Random rand = new Random();

    ArrayList<Cell> untouched;
    ArrayList<Cell> progressed;

    /**
     *
     * @param numberAmount Number of Cell that should be solved at the beginning.
     * @return Field with numberAmount solved cells.
     */

    public Field generateNewField(int numberAmount) {

        for (int i = 1; i <= 9; i++) {
            int xRand = rand.nextInt(8);
            int yRand = rand.nextInt(8);
            field.setValue(xRand, yRand, i);
        }
        Solver solver = new Solver(field);
        solver.solve();

        int toDelete = 81 - numberAmount;

        Field fOrigin = field.clone();
        while( !deleteCells(toDelete) ){
            field = fOrigin.clone();
        }
        for( int x = 0; x < 9; x++ ){
            for( int y = 0; y < 9; y ++ ){
                if( field.getValue(x,y) == 0){
                    fOrigin.getCell(x,y).setVisible( false );
                }
                else{
                    fOrigin.getCell(x,y).setFromBeginning( true );
                }
            }
        }

        return fOrigin;

    }

    public boolean deleteCells(int toDelete) {
        ArrayList<Cell> untouched = new ArrayList<Cell>();
        Solver solver = new Solver(field);
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                untouched.add(field.getCell(x, y));
            }
        }
        while (untouched.size() > 0 && toDelete != 0) {
            int rNum = rand.nextInt(untouched.size());
            int oldValue = untouched.get(rNum).getValue();
            untouched.get(rNum).setValue(0);
            if (solver.isUnique()) {
                toDelete--;
            } else {
                untouched.get(rNum).setValue(oldValue);
            }
            untouched.remove(rNum);
        }
        if (toDelete == 0) {
            return true;
        }
        return false;
    }

}