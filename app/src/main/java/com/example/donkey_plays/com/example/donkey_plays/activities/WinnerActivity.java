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
import com.example.donkey_plays.com.example.donkey_plays.models.Player;
import com.example.donkey_plays.com.example.donkey_plays.services.PlayerService;

import java.util.HashMap;
import java.util.List;

public class WinnerActivity extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getSupportActionBar().hide();
        hideSystemUI();
        setContentView(R.layout.winner);
        Game game = GameState.getGame();
        Minigame currentMinigame = game.getCurrentMinigame();

        textView = findViewById(R.id.winner);

        HashMap.Entry<String, Double> maxEntry = currentMinigame.getWinner();

        PlayerService playerService = new PlayerService();
        playerService.increaseScore(maxEntry.getKey());

        textView.setText(maxEntry.getKey() + " won!");

        new CountDownTimer(5000, 500) {
            public void onTick(long millisUntilFinished) {


            }

            public void onFinish() {
                Intent i = new Intent(WinnerActivity.this, StandingActivity.class);
                startActivity(i);
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
