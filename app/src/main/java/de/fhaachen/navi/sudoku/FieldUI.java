package de.fhaachen.navi.sudoku;

import android.content.Context;
import android.graphics.Color;
import android.widget.Button;

public class FieldUI {
    private int COLOR_USER = Color.BLUE;
    private int COLOR_GENERATOR = Color.BLACK;
    private int COLOR_BACKGROUND = Color.WHITE;

    private TextField[][] sudoku;
    private int SIZE = 9;
    private Context context;
    private Button activeButton;

    public FieldUI(Context context) {
        sudoku = new TextField[SIZE][SIZE];
        this.context = context;
    }

    public FieldUI(Field f, Context context, int size) {
        this.context = context;
        sudoku = new TextField[SIZE][SIZE];
        for(int i = 0; i < 9; i ++) {
            for(int j = 0; j < 9; j++) {
                int number = f.getCell(i,j).getValue();
                TextField textField = new TextField(context);
                textField.setMinimumWidth(size);
                textField.setWidth(size);
                textField.setMinimumHeight(size);
                textField.setHeight(size);
                if(number == 0) {
                    textField.setTextColor(COLOR_USER);
                    textField.setText(" ");
                } else {
                    textField.setTextColor(COLOR_GENERATOR);
                    textField.setText(number + "");
                }
                textField.setBackgroundColor(COLOR_BACKGROUND);
                // textField.setTextSize(size);
                sudoku[i][j] = textField;
            }
        }
    }

    public TextField getTextField(int x, int y) {
        return sudoku[x][y];
    }
}