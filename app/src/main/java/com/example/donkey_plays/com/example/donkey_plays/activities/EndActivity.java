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

        winner.setText("Winner: " + winnerPlayer.getName());

        resetGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GameState.resetGame();
                Intent i = new Intent(EndActivity.this, MainActivity.class);
                startActivity(i);
            }
        });

    }
}
