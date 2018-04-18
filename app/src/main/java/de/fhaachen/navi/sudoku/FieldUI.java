package de.fhaachen.navi.sudoku;

import android.content.Context;
import android.graphics.Color;
import android.view.View;

public class FieldUI {
    private int COLOR_USER = Color.BLUE;
    private int COLOR_GENERATOR = Color.BLACK;
    private int COLOR_NOT_SELECTED = Color.WHITE;
    private int COLOR_SELECTED = Color.GRAY;

    private TextField[][] sudoku;
    private TextField currentTextField;
    private int SIZE = 9;
    private Context context;

    public FieldUI(Field f, Context context, int size) {
        this.context = context;
        sudoku = new TextField[SIZE][SIZE];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                int number = f.getCell(i, j).getValue();
                final TextField textField = new TextField(context);
                textField.setMinimumWidth(size);
                textField.setWidth(size);
                textField.setMinimumHeight(size);
                textField.setHeight(size);
                if (number == 0) {
                    textField.setTextColor(COLOR_USER);
                    textField.setText(" ");
                    //TODO SCHRIFT FETT
                } else {
                    textField.setTextColor(COLOR_GENERATOR);
                    textField.setText(number + "");
                }
                textField.setBackgroundColor(COLOR_NOT_SELECTED);
                // textField.setTextSize(size);
                sudoku[i][j] = textField;

                textField.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        TextField oldTextField = currentTextField;
                        currentTextField = textField;
                        if (oldTextField != null) {
                            oldTextField.setBackgroundColor(COLOR_NOT_SELECTED);
                        }
                        if (currentTextField.getCurrentTextColor() != COLOR_GENERATOR) {
                            currentTextField.setBackgroundColor(COLOR_SELECTED);
                        }
                    }
                });
            }
        }
    }

    public TextField getTextField(int x, int y) {
        return sudoku[x][y];
    }

    public TextField getCurrentTextField() {
        return currentTextField;
    }

    public int getCOLOR_GENERATOR() {
        return COLOR_GENERATOR;
    }
}