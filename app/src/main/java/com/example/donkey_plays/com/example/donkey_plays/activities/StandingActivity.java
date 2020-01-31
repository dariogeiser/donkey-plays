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
import com.example.donkey_plays.com.example.donkey_plays.models.Player;
import com.example.donkey_plays.com.example.donkey_plays.services.PlayerService;

import java.util.List;

public class StandingActivity extends AppCompatActivity {

    TextView player1;
    TextView player2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getSupportActionBar().hide();
        hideSystemUI();
        setContentView(R.layout.standing);

        player1 = findViewById(R.id.player1);
        player2 = findViewById(R.id.player2);


        PlayerService playerService = new PlayerService();

        List<Player> players = playerService.getPlayers();
        player1.setText(players.get(0).getName() + ": " + players.get(0).getScore());
        player2.setText(players.get(1).getName() + ": " + players.get(1).getScore());


        new CountDownTimer(5000, 500) {
            public void onTick(long millisUntilFinished) {


            }

            public void onFinish() {

                Game game = GameState.getGame();
                Intent i;
                if (game.hasNewMiniGame()) {
                    i = new Intent(StandingActivity.this, IntroductionActivity.class);
                    i.putExtra("newMinigame", true);
                } else {
                    i = new Intent(StandingActivity.this, EndActivity.class);
                }

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
