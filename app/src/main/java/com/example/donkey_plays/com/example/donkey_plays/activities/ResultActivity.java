package com.example.donkey_plays.com.example.donkey_plays.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
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
        hideSystemUI();
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
                if (currentMinigame.setCurrentPlayer()) {
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

    private void hideSystemUI() {
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
                        | View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
                        | View.SYSTEM_UI_FLAG_IMMERSIVE);
    }
}
