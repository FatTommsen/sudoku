package de.fhaachen.navi.sudoku;

import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;

public class GameActivity extends AppCompatActivity {
    private int difficulty;
    private FieldUI fieldUI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        difficulty = intent.getExtras().getInt("difficulty");
        System.out.println(difficulty);
        setContentView(R.layout.game_view);

        createPlayField();
    }

    private void createPlayField() {
        createGrid();
        createButtons();

    }

    private void createGrid() {
        GridLayout gl = findViewById(R.id.gridlayout);

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x - 16;

        fieldUI = new FieldUI(this, width / 9, difficulty);

        for (int i = 0; i < 9; i++) {
            GridLayout g = new GridLayout(this);
            g.setRowCount(3);
            g.setColumnCount(3);
            g.setBackground(this.getResources().getDrawable(R.drawable.box_border));
            for (int j = 0; j < 9; j++) {
                g.addView(fieldUI.getBoxes().get(i).get(j));
            }
            gl.addView(g);
        }
    }

    private void createButtons() {
        Button[] buttons = new Button[9];
        buttons[0] = findViewById(R.id.button1);
        buttons[1] = findViewById(R.id.button2);
        buttons[2] = findViewById(R.id.button3);
        buttons[3] = findViewById(R.id.button4);
        buttons[4] = findViewById(R.id.button5);
        buttons[5] = findViewById(R.id.button6);
        buttons[6] = findViewById(R.id.button7);
        buttons[7] = findViewById(R.id.button8);
        buttons[8] = findViewById(R.id.button9);
        for (int i = 0; i < 9; i++) {
            final int n = i + 1;
            buttons[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    TextField textField = fieldUI.getCurrentTextField();
                    if (textField != null && textField.getCurrentTextColor() != fieldUI.getCOLOR_GENERATOR()) {
                        textField.setText("" + n);
                        fieldUI.setCurrentTextField(null);
                    }
                }
            });
        }

        Button buttonX = findViewById(R.id.buttonX);
        buttonX.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextField textField = fieldUI.getCurrentTextField();
                if (textField != null && textField.getCurrentTextColor() != fieldUI.getCOLOR_GENERATOR()) {
                    textField.setText(" ");
                    fieldUI.setCurrentTextField(null);
                }
            }
        });
    }
}