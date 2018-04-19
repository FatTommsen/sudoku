package de.fhaachen.navi.sudoku;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);
    }

    /**
     * Methode für den newGame Button
     *
     * @param view
     */
    public void createNewGame(View view) {
        Intent levels = new Intent(getApplicationContext(), LevelActivity.class);
        startActivity(levels);
    }

    /**
     * Methode für den solver Button
     *
     * @param view
     */
    public void createSolver(View view){
        Intent solver = new Intent(getApplicationContext(), SolverActivity.class);
        startActivity(solver);
    }


    /**
     * Methode zum aufrufen der Regeln
     *
     * @param view
     */
    public void info(View view) {
        Intent info = new Intent(getApplicationContext(), RulesActivity.class);
        startActivity(info);
    }


}
