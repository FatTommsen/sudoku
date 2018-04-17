package de.fhaachen.navi.sudoku;


public class Generator {
    Field field = new Field();

    // Level : schwer: 18-24 mittel: 25-31 leicht: 32 - 38
    public Field generateNewField(int level) {

        for (int i = 1; i <= 9; i++) {
            int xRand = (int) Math.random() * 8;
            int yRand = (int) Math.random() * 8;
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

    public boolean deleteCells(int toDelete) {
        if( toDelete == 0 ){
            return true;
        }
        int xRand;
        int yRand;
        int oldValue;
        do {
            do {
                xRand = (int) Math.random() * 8;
                yRand = (int) Math.random() * 8;
            } while (field.getValue(xRand, yRand) == 0);
            oldValue = field.getValue(xRand, yRand);
            field.setValue(xRand, yRand, 0);
        } while (!new Solver(field).isUnique());

        deleteCells( toDelete - 1 );
        return true;
    }
}