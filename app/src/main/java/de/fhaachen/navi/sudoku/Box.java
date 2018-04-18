package de.fhaachen.navi.sudoku;

import java.util.ArrayList;
import java.util.List;

public class Box {
    List<Cell> values;

    public Box(List<Cell> values){
        this.values = values;
    }

    public Box(){
        values = new ArrayList<Cell>();
    }

    public void setValues(Cell cell){
        values.add(cell);
    }

    public boolean isValid( boolean bInProgress ){
        boolean[] bValues = new boolean[9];
        for( Cell c : values ){
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

    public Cell getCell( int number ){
        return values.get(number);
    }
}
