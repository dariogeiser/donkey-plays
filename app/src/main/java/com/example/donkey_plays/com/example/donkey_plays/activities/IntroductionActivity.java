package com.example.donkey_plays.com.example.donkey_plays.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.donkey_plays.R;
import com.example.donkey_plays.com.example.donkey_plays.models.Game;
import com.example.donkey_plays.com.example.donkey_plays.models.GameState;
import com.example.donkey_plays.com.example.donkey_plays.models.Minigame;

public class IntroductionActivity extends AppCompatActivity {

    TextView playerName;
    Button buttonStartGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getSupportActionBar().hide();
        Intent currentIntent = getIntent();
        boolean newMiniGame = currentIntent.getExtras().getBoolean("newMinigame");

        Game game = GameState.getGame();

        Minigame currentMinigame;
        if(newMiniGame) {
            currentMinigame = game.getNewMinigame();
        } else {
            currentMinigame = game.getCurrentMinigame();
        }
        GameState.setGame(game);


        setContentView(currentMinigame.getId());


        playerName = findViewById(R.id.playerName);
        buttonStartGame = findViewById(R.id.buttonStartGame);

        playerName.setText(currentMinigame.getCurrentPlayer().getName());


        buttonStartGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Game game = GameState.getGame();
                game.loadMinigames();
                GameState.setGame(game);
                Intent i = new Intent(IntroductionActivity.this, game.getCurrentMinigame().getGameActivity());
                startActivity(i);
            }
        });

    }

}

