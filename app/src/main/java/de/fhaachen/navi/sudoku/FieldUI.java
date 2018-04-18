package de.fhaachen.navi.sudoku;

import android.content.Context;
import android.graphics.Color;
import android.widget.Button;

import java.util.ArrayList;

public class FieldUI {

    private TextField[][] sudoku;
    private ArrayList<ArrayList<TextField>> boxes = new ArrayList<ArrayList<TextField>>();
    private int SIZE = 9;
    private Context context;
    private Button activeButton;
    private Field f;

    public FieldUI(Context context) {
        sudoku = new TextField[SIZE][SIZE];
        this.context = context;
    }

    public FieldUI( Context context, int size, int difficulty) {
        for( int i = 0; i < 9; i++ ){
            boxes.add(new ArrayList<TextField>());
        }
        this.context = context;
        Generator gen = new Generator();
        f = gen.generateNewField(difficulty);

        sudoku = new TextField[SIZE][SIZE];

        for(int i = 0; i < 9; i ++) {
            for(int j = 0; j < 9; j++) {
                int number = f.getCell(i,j).getValue();
                TextField textField = new TextField(context, f.getCell( j, i));
                textField.setMinimumWidth(size-6);
                textField.setWidth(size-6);
                textField.setMinimumHeight(size-6);
                textField.setHeight(size-6);

              //  textField.setBackgroundColor(COLOR_BACKGROUND);
                // textField.setTextSize(size);
                sudoku[j][i] = textField;
                boxes.get((i/3)*3 + j/3).add(textField);
            }
        }
    }

    public TextField getTextField(int x, int y) {
        return sudoku[x][y];
    }

    public ArrayList<ArrayList<TextField>> getBoxes() {
        return boxes;
    }
}