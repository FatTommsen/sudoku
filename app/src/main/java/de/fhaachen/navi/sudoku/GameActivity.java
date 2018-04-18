package de.fhaachen.navi.sudoku;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class GameActivity extends AppCompatActivity{

        private Intent intent;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            intent = getIntent();
            System.out.println(intent.getExtras().getInt("difficulty"));
            setContentView(R.layout.game_view);
        }



}