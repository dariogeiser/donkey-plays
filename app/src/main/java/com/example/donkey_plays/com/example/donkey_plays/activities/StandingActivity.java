package com.example.donkey_plays.com.example.donkey_plays.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
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
                if(game.hasNewMiniGame()){
                    i = new Intent(StandingActivity.this, IntroductionActivity.class);
                    i.putExtra("newMinigame", true);
                } else {
                    i = new Intent(StandingActivity.this, EndActivity.class);
                }

                startActivity(i);
            }
        }.start();
    }
}
