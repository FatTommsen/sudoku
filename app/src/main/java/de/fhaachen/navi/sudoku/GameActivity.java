package de.fhaachen.navi.sudoku;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class GameActivity extends AppCompatActivity{

        Intent intent;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            intent = getIntent();
            setContentView(R.layout.game_view);
            int zahl = Integer.parseInt(intent.getStringExtra("difficulty"));
            System.out.println(zahl);
        }



}