package de.fhaachen.navi.sudoku;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.Display;
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

        GridLayout gl = findViewById(R.id.gridlayout);

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x - 16;

        FieldUI fieldUI = new FieldUI( this, width / 9, difficulty);

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
}