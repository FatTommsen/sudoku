package de.fhaachen.navi.sudoku;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;

public class SolverActivity extends AppCompatActivity {
    private FieldUI fieldUI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.solver_view);
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
        int pixel = (width / 9) - 6;

        int difficulty = 81;
        Field field = new Field();
        field.setAllCellsInvisble();
        fieldUI = new FieldUI(field, this, pixel, difficulty);

        for (int i = 0; i < 9; i++) {
            GridLayout g = new GridLayout(this);
            g.setRowCount(3);
            g.setColumnCount(3);
            for (int j = 0; j < 9; j++) {
                g.addView(fieldUI.getBoxes().get(i).get(j));
            }
            g.setBackground(this.getResources().getDrawable(R.drawable.box_border));
            g.setPadding(6, 6, 6, 6);
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
                    if (textField != null && !fieldUI.isFromBeginning(textField)) {
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
                if (textField != null && !fieldUI.isFromBeginning(textField)) {
                    textField.setText(" ");
                    fieldUI.setCurrentTextField(null);
                }
            }
        });
    }

    public void solveSudoku(View view){
        for (int i = 0; i < 9; i++){
            for (int j = 0; j < 9; j++){
                TextField tf = fieldUI.getTextField(i, j);
                if (!tf.getText().toString().equals(" ")){
                    tf.setTextColor(Color.BLACK);
                }

            }
        }
        fieldUI.solveTheSudoku();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.action_menu_game_activity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.action_newGame:
                Intent solver = new Intent(getApplicationContext(),SolverActivity.class);
                startActivity(solver);
            case R.id.action_deleteUserEntry:
                fieldUI.reset();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
