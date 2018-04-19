package de.fhaachen.navi.sudoku;

import android.content.Context;
import android.graphics.Color;
import android.view.View;

import java.util.ArrayList;

public class FieldUI {
    private int COLOR_USER = Color.BLUE;
    private int COLOR_GENERATOR = Color.BLACK;
    private int COLOR_NOT_SELECTED = Color.WHITE;
    private int COLOR_SELECTED = Color.GRAY;

    private TextField[][] sudoku;
    private TextField currentTextField;
    private int SIZE = 9;
    private Context context;
    private ArrayList<ArrayList<TextField>> boxes = new ArrayList<ArrayList<TextField>>();
    private Field f;


    public FieldUI(Context context, int size, int difficulty) {
        for (int i = 0; i < 9; i++) {
            boxes.add(new ArrayList<TextField>());
        }
        this.context = context;
        Generator gen = new Generator();
        f = gen.generateNewField(difficulty);

        sudoku = new TextField[SIZE][SIZE];

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                final TextField textField = new TextField(context, f.getCell(j, i));

                textField.setMinimumWidth(size - 6);
                textField.setWidth(size -6);
                textField.setMinimumHeight(size - 6);
                textField.setHeight(size - 6);

                // textField.setTextSize(size);
                boxes.get((i / 3) * 3 + j / 3).add(textField);
                sudoku[i][j] = textField;

                textField.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        TextField oldTextField = currentTextField;
                        currentTextField = textField;
                        if (oldTextField != null) {
                            oldTextField.setBackground(oldTextField.getResources().getDrawable(R.drawable.cell_border));

                            // oldTextField.setBackgroundColor(COLOR_NOT_SELECTED);
                        }
                        if (currentTextField.getCurrentTextColor() != COLOR_GENERATOR) {
                            currentTextField.setBackground(currentTextField.getResources().getDrawable(R.drawable.cell_marked));
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

    public ArrayList<ArrayList<TextField>> getBoxes() {
        return boxes;
    }
}