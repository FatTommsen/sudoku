package de.fhaachen.navi.sudoku;

import java.util.Random;

public class Generator {
    Field field = new Field();
    Random rand = new Random();

    // Level : schwer: 18-24 mittel: 25-31 leicht: 32 - 38
    public Field generateNewField(int level) {

        for (int i = 1; i <= 9; i++) {
            int xRand = rand.nextInt(8);
            int yRand = rand.nextInt(8);
            field.setValue(xRand, yRand, i);
        }
        Solver solver = new Solver(field);
        solver.backtrack();

        if( level == 1 ){
            deleteCells( 81 - 20);
        }
        else if ( level == 2){
            deleteCells( 81 - 29);
        }
        else {
            deleteCells( 81 - 35);
        }
        return field;

    }


    public void deleteCells(int toDelete) {
        int counter = toDelete;
        while( counter > 0 ){
            int xRand;
            int yRand;
            int oldValue;
            do {
                xRand = rand.nextInt(9);
                yRand = rand.nextInt(9);
            } while (field.getValue(xRand, yRand) == 0);
            oldValue = field.getValue(xRand, yRand);
            field.setValue(xRand, yRand, 0);
            if( new Solver(field).isUnique() ){
                counter--;
            }
            else {
                field.setValue(xRand, yRand, oldValue);
            }
        }
    }
}