package de.fhaachen.navi.sudoku;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class FieldUI {
    private int COLOR_GENERATOR = Color.BLACK;

    private TextField[][] sudoku;
    private TextField currentTextField;
    private int SIZE = 9;
    private Context context;
    private ArrayList<ArrayList<TextField>> boxes = new ArrayList<>();
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
                textField.setWidth(size - 6);
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
                            setNotSelected(oldTextField);
                        }
                        if (!currentTextField.getCell().isFromBeginning()) {
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

    public int getCOLOR_GENERATOR() {
        return COLOR_GENERATOR;
    }

    public ArrayList<ArrayList<TextField>> getBoxes() {
        return boxes;
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
                                    return;
                                }
                            })
                            .setPositiveButton("alles zurücksetzen.", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    //GameActivity.deleteUserEntries;
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
                                return;
                            }
                        })
                        .show();
        }
    }



    public void rollback( ArrayList<TextField> cells ){
        for( TextField tmp : cells ){
            tmp.setBackground(tmp.getResources().getDrawable(R.drawable.cell_border));
        }
    }
}