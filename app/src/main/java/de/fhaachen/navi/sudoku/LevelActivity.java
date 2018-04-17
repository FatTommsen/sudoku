package de.fhaachen.navi.sudoku;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class LevelActivity extends AppCompatActivity {
    private int EASY = 1;
    private int MEDIUM = 2;
    private int HARD = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.levels);
    }

    /**
     * Erstellt ein neues Spiel der Schwierigkeit Einfach
     *
     * @param view
     */
    public void levelModeEasy(View view){
<<<<<<< HEAD
        Intent leveMainls = new Intent(getApplicationContext(), GameActivity.class);
=======
        Intent levels = new Intent(getApplicationContext(), GameActivity.class);
>>>>>>> 8bfbfc04a1e610fa75defb301ba103abcec837e2
        levels.putExtra("difficulty", 1);
        startActivity(levels);
    }

    /**
     * Erstellt ein neues Spiel der Schwierigkeit Mittel
     *
     * @param view
     */
    public void levelModeMedium(View view){
        Intent levels = new Intent(getApplicationContext(), GameActivity.class);
        levels.putExtra("difficulty", 2);
        startActivity(levels);
    }

    /**
     * Erstellt ein neues Spiel der Schwierigkeit Schwer
     *
     * @param view
     */
    public void levelModeHard(View view){
        Intent levels = new Intent(getApplicationContext(), GameActivity.class);
        levels.putExtra("difficulty", 3);
        startActivity(levels);
    }


}
