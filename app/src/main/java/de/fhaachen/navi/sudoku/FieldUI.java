package de.fhaachen.navi.sudoku;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import android.graphics.Color;
import android.view.View;

import java.util.ArrayList;

public class FieldUI {
    private TextField[][] sudoku;
    private TextField currentTextField;
    private int SIZE = 9;
    private Context context;
    private ArrayList<ArrayList<TextField>> boxes = new ArrayList<>();
    private final Field f;


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
                textField.setSize(size);

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




    public void solveTheSudoku() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (!sudoku[i][j].getText().toString().equals(" ")) {
                    int value = Integer.parseInt(sudoku[i][j].getText().toString());
                    sudoku[i][j].getCell().setValue(value);
                }
            }
        }
        Solver solver = new Solver(f);
        Solver.Mode mode = solver.analyse();
        if (mode == Solver.Mode.UniqueTest) {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("")
                    .setMessage("Das Sudoku hat keine Lösung.")
                    .setNegativeButton("zurück", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            f.resetAllValues();
                            return;
                        }
                    })
                    .show();
            //Meldung an User nicht nur eine Lösung vorhanden
        }
        else{
            solver.solve();
            if (mode == Solver.Mode.UniqueAbort) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("")
                        .setMessage("Das Sudoku hat mehrere Lösungen.\nDies ist eine davon.")
                        .setNegativeButton("Okay", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                return;
                            }
                        })
                        .show();
            }
            showSolution();
            f.resetAllValues();
        }
    }





    public void checkField() {
        final ArrayList<TextField> falseValue = new ArrayList<TextField>();
        final ArrayList<TextField> emptyCells = new ArrayList<TextField>();

        boolean bValid = true;
        boolean bComplete = true;
        //analyze
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                TextField tmp = sudoku[x][y];
                if (sudoku[x][y].getText().toString().equals(" ")) {
                    bComplete = false;
                    emptyCells.add(tmp);
                } else if (bComplete) {
                    if (tmp.getCell().isFromBeginning()) {
                        continue;
                    } else {
                        Integer value = Integer.parseInt(tmp.getText().toString());
                        if (value != tmp.getCell().getValue()) {
                            falseValue.add(tmp);
                            bValid = false;
                        }
                    }
                }
            }
        }
        //feedback
        //test:
        //bComplete = true;
       // bValid = false;
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        if (bComplete) {

            if (bValid) {
                builder.setTitle("")
                        .setMessage("Richtig.")
                        .setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                               // rollback(emptyCells);
                                setCurrentTextFieldNull();
                                for(int i = 0; i < 9; i++) {
                                    for(int j = 0; j < 9; j++) {
                                        sudoku[i][j].setFromBeginning(true);
                                    }
                                }
                                return;
                            }
                        })
                        .show();
            } else {
                for (TextField tmp : falseValue) {
                    tmp.setBackground(tmp.getResources().getDrawable(R.drawable.cell_wrong_value));
                }
                    builder.setTitle("")
                            .setMessage("Die markierten Felder sind leider\nfalsch gelöst.")
                            .setNegativeButton("Okay", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    //rollback(emptyCells);
                                    if(currentTextField != null && !falseValue.contains(currentTextField)) {
                                        setNotSelected(currentTextField);
                                    }
                                    currentTextField = null;
                                    return;
                                }
                            })
                            .setPositiveButton("alles zurücksetzen.", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    GameActivity.deleteUserEntries();
                                    setCurrentTextFieldNull();
                                    for(TextField textField : falseValue) {
                                        setNotSelected(textField);
                                    }
                                    return;
                                }
                            })
                            .show();
            }
        } else {
            for (TextField tmp : emptyCells) {
                tmp.setBackground(tmp.getResources().getDrawable(R.drawable.cell_incomplete));
            }
                builder.setTitle("")
                        .setMessage("Es müssen alle 81 Felder ausgeüllt sein.")
                        .setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                              //  rollback(emptyCells);
                                if(currentTextField != null && !emptyCells.contains(currentTextField)) {
                                    setNotSelected(currentTextField);
                                }
                                currentTextField = null;
                                return;
                            }
                        })
                        .show();
        }
    }



    public void rollback( ArrayList<TextField> cells ){
        for( TextField tmp : cells ){
            setNotSelected(tmp);
        }
    }

    public int getSIZE(){return SIZE;}

    public TextField getTextField( int x, int y) {
        return sudoku[x][y];

    }

    public void showSolution(){
        for( TextField[] row : sudoku ){
            for( TextField tmp : row ){
                tmp.setText( tmp.getCell().getValue() + "");
            }
        }
    }

    public void setCurrentTextFieldNull() {
        if(currentTextField != null) {
            setNotSelected(currentTextField);
        }
        currentTextField = null;
    }

    public void selectNothing() {
        for(int i = 0; i < 9; i++) {
            for(int j = 0; j < 9; j++) {
                setNotSelected(sudoku[i][j]);
            }
        }
    }

    public void giveAHint() {
        if(currentTextField != null) {
            currentTextField.setText("" + currentTextField.getCell().getValue());
            currentTextField.setTextColor(TextField.getColorHint());
            currentTextField.setFromBeginning(true);
            setCurrentTextFieldNull();
        }
    }
}