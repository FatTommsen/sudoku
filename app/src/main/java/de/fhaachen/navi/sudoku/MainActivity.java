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
    public void createNewGame(View view){
        // neue activity -> Schwierigkeiten
        Intent levels = new Intent(getApplicationContext(), LevelActivity.class);
        startActivity(levels);
    }

    /**
     * Methode für den solver Button
     *
     * @param view
     */
    public void createSolver(View view){

    }

    /**
     * Methode für meine Sudokus
     *
     * @param view
     */
    public void mySudokus(View view){

    }

    /**
     * Methode zum aufrufen der Regeln
     *
     * @param view
     */
    public void rules(View view){

    }


}
