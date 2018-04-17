package de.fhaachen.navi.sudoku;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class LevelActivity extends AppCompatActivity {

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

    }

    /**
     * Erstellt ein neues Spiel der Schwierigkeit Mittel
     *
     * @param view
     */
    public void levelModeMiddle(View view){

    }

    /**
     * Erstellt ein neues Spiel der Schwierigkeit Schwer
     *
     * @param view
     */
    public void levelModeHard(View view){

    }


}
