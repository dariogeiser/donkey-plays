package com.example.donkey_plays.com.example.donkey_plays.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.donkey_plays.R;
import com.example.donkey_plays.com.example.donkey_plays.models.Game;
import com.example.donkey_plays.com.example.donkey_plays.models.GameState;
import com.example.donkey_plays.com.example.donkey_plays.models.Minigame;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getSupportActionBar().hide();
        Game game = GameState.getGame();
        Minigame currentMinigame = game.getCurrentMinigame();
        setContentView(currentMinigame.getResultRef());
    }
}
