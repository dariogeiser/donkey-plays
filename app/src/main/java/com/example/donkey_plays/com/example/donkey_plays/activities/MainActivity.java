package com.example.donkey_plays.com.example.donkey_plays.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.donkey_plays.R;
import com.example.donkey_plays.com.example.donkey_plays.models.Game;
import com.example.donkey_plays.com.example.donkey_plays.models.GameState;
import com.example.donkey_plays.com.example.donkey_plays.models.Player;
import com.example.donkey_plays.com.example.donkey_plays.services.MusicService;
import com.example.donkey_plays.com.example.donkey_plays.services.PlayerService;
import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {

    private Button buttonPlayer1;
    private Button buttonPlayer2;
    private Button buttonStartGame;

    private TextInputLayout player1Input;
    private TextInputLayout player2Input;

    private TextView numberOfPlayers;

    private PlayerService playerService = new PlayerService();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getSupportActionBar().hide();
        hideSystemUI();
        setContentView(R.layout.start);

        Intent svc = new Intent(this, MusicService.class);
        startService(svc);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO},
                    0);

        }

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.VIBRATE) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.VIBRATE},
                    0);

        }

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACTIVITY_RECOGNITION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACTIVITY_RECOGNITION},
                    0);

        }


        buttonPlayer1 = findViewById(R.id.buttonPlayer1);
        buttonPlayer2 = findViewById(R.id.buttonPlayer2);
        buttonStartGame = findViewById(R.id.buttonStartGame);

        player1Input = findViewById(R.id.player1);
        player2Input = findViewById(R.id.player2);

        numberOfPlayers = findViewById(R.id.numberOfPlayers);


        buttonPlayer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String playerName = player1Input.getEditText().getText().toString();

                if (playerService.addPlayer(new Player(playerName))) {
                    numberOfPlayers.setText("Number of Players: " + playerService.getPlayers().size());
                }
            }
        });

        buttonPlayer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String playerName = player2Input.getEditText().getText().toString();

                if (playerService.addPlayer(new Player(playerName))) {
                    numberOfPlayers.setText("Number of Players: " + playerService.getPlayers().size());
                }
            }
        });

        buttonStartGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Game game = GameState.getGame();
                game.loadMinigames();
                GameState.setGame(game);
                Intent i = new Intent(MainActivity.this, IntroductionActivity.class);
                i.putExtra("newMinigame", true);
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
