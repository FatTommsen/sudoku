package de.fhaachen.navi.sudoku;

import android.content.Context;
import android.widget.Button;

public class FieldUI {
    private TextField[][] sudoku;
    private int SIZE = 9;
    private Context context;
    private Button activeButton;

    public FieldUI(Context context) {
        sudoku = new TextField[SIZE][SIZE];
        this.context = context;
    }

    public FieldUI(Field f, Context context) {
        this.context = context;
        sudoku = new TextField[SIZE][SIZE];
        for(int i = 0; i < 9; i ++) {
            for(int j = 0; j < 9; j++) {
                int number = f.getCell(i,j).getValue();
                TextField textField = new TextField(context);
                textField.setText(number == 0 ? " " : number + "");
                sudoku[i][j] = textField;
            }
        }
    }

    public TextField getTextField(int x, int y) {
        return sudoku[x][y];
    }
}