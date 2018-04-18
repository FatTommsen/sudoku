package de.fhaachen.navi.sudoku;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridLayout;
import android.widget.TextView;

import java.util.logging.Logger;

public class GameActivity extends AppCompatActivity {
    private int AMOUNT_OF_TEXTFIELDS = 81;
    private int difficulty;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        difficulty = intent.getExtras().getInt("difficulty");
        System.out.println(difficulty);
        setContentView(R.layout.game_view);
        createGrid();
    }

    private void createGrid() {
        Generator gen = new Generator();
        Field f = gen.generateNewField(difficulty);

        GridLayout gl = findViewById(R.id.gridlayout);
        FieldUI fieldUI = new FieldUI(f, this);
        for (int i = 0; i < AMOUNT_OF_TEXTFIELDS; i++) {
            TextField newTextField = new TextField(this);
            String text = "" + i;
            newTextField.setText(text);
        }

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                gl.addView(fieldUI.getTextField(i, j));
            }
        }
    }
}