package de.fhaachen.navi.sudoku;

import android.content.Context;
import android.view.View;

import java.util.ArrayList;

public class FieldUI {
    private TextField[][] sudoku;
    private TextField currentTextField;
    private int SIZE = 9;
    private Context context;
    private ArrayList<ArrayList<TextField>> boxes = new ArrayList<>();
    private Field f;


    public FieldUI(Field f, Context context, int size, int difficulty) {
        for (int i = 0; i < 9; i++) {
            boxes.add(new ArrayList<TextField>());
        }
        this.context = context;

        if (f == null) {
            Generator gen = new Generator();
            f = gen.generateNewField(difficulty);
        }
        this.f = f;

        sudoku = new TextField[SIZE][SIZE];

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                final TextField textField = new TextField(context, f.getCell(j, i));

                textField.setMinimumWidth(size - 6);
                textField.setWidth(size - 6);
                textField.setMinimumHeight(size - 6);
                textField.setHeight(size - 6);

                boxes.get((i / 3) * 3 + j / 3).add(textField);
                sudoku[i][j] = textField;

                textField.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        TextField oldTextField = currentTextField;
                        currentTextField = textField;
                        if (oldTextField != null) {
                            setNotSelected(oldTextField);
                        }
                        if (!isFromBeginning(currentTextField)) {
                            setSelected(currentTextField);
                        }
                    }
                });

            }
        }
    }

    public TextField getCurrentTextField() {
        return currentTextField;
    }

    public void setCurrentTextField(TextField currentTextField) {
        setNotSelected(this.currentTextField);
        this.currentTextField = currentTextField;
    }

    public void setSelected(TextField textField) {
        textField.setBackground(textField.getResources().getDrawable(R.drawable.cell_marked));
    }

    public void setNotSelected(TextField textField) {
        textField.setBackground(textField.getResources().getDrawable(R.drawable.cell_border));
    }

    public boolean isFromBeginning(TextField textField) {
        return textField.isFromBeginning();
    }

    public ArrayList<ArrayList<TextField>> getBoxes() {
        return boxes;
    }

    public void solveTheSudoku(){
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if(!sudoku[i][j].getText().toString().equals(" ")){
                    int value = Integer.parseInt(sudoku[i][j].getText().toString());
                    sudoku[i][j].getCell().setValue(value);  //getCell muss noch geschrieben werden
                }
            }
        }
        Solver solver = new Solver(f);
        if(!solver.isUnique()){
            //Meldung an User nicht nur eine Lösung vorhanden
        }
        solver.backtrack();
    }
}