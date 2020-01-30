package com.example.donkey_plays.com.example.donkey_plays.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.TextView;

import com.example.donkey_plays.R;
import com.example.donkey_plays.com.example.donkey_plays.models.Game;
import com.example.donkey_plays.com.example.donkey_plays.models.GameState;
import com.example.donkey_plays.com.example.donkey_plays.models.Minigame;

import java.util.Collections;

public class ResultActivity extends AppCompatActivity {



    TextView value;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getSupportActionBar().hide();
        Game game = GameState.getGame();
        Minigame currentMinigame = game.getCurrentMinigame();
        setContentView(currentMinigame.getResultRef());

        Intent currentIntent = getIntent();
        final String intentValue = currentIntent.getStringExtra("value");
        value = findViewById(R.id.value);

        value.setText(intentValue);


        new CountDownTimer(5000, 500) {
            public void onTick(long millisUntilFinished) {


            }

            public void onFinish() {
                Game game = GameState.getGame();
                Minigame currentMinigame = game.getCurrentMinigame();
                if(currentMinigame.setCurrentPlayer()){
                    GameState.setGame(game);
                    Intent i = new Intent(ResultActivity.this, IntroductionActivity.class);
                    i.putExtra("newMinigame", false);
                    startActivity(i);
                } else {
                    GameState.setGame(game);
                    Intent i = new Intent(ResultActivity.this, WinnerActivity.class);
                    startActivity(i);
                }

            }
        }.start();
    }
}
