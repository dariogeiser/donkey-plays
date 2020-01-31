package com.example.donkey_plays.com.example.donkey_plays.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.donkey_plays.R;
import com.example.donkey_plays.com.example.donkey_plays.models.GameState;
import com.example.donkey_plays.com.example.donkey_plays.models.Player;
import com.example.donkey_plays.com.example.donkey_plays.services.MusicService;
import com.example.donkey_plays.com.example.donkey_plays.services.PlayerService;

import java.util.List;

public class EndActivity extends AppCompatActivity {

    TextView player1;
    TextView player2;
    TextView winner;

    Button resetGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getSupportActionBar().hide();
        hideSystemUI();
        setContentView(R.layout.end);

        player1 = findViewById(R.id.player1);
        player2 = findViewById(R.id.player2);
        winner = findViewById(R.id.winner);

        resetGame = findViewById(R.id.resetGame);

        PlayerService playerService = new PlayerService();

        List<Player> players = playerService.getPlayers();
        player1.setText(players.get(0).getName() + ": " + players.get(0).getScore());
        player2.setText(players.get(1).getName() + ": " + players.get(1).getScore());

        Player winnerPlayer = playerService.getWinner();

        if (winnerPlayer == null) {
            winner.setText("Winner: Both");
        } else {
            winner.setText("Winner: " + winnerPlayer.getName());
        }


        resetGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent svc = new Intent(EndActivity.this, MusicService.class);
                stopService(svc);
                GameState.resetGame();
                Intent i = new Intent(EndActivity.this, MainActivity.class);
                startActivity(i);
            }
        });

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
