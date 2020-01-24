package com.example.donkey_plays;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.example.donkey_plays.com.example.donkey_plays.activities.VoiceGameIntroductionActivity;
import com.example.donkey_plays.com.example.donkey_plays.models.Game;
import com.example.donkey_plays.com.example.donkey_plays.models.GameState;
import com.example.donkey_plays.com.example.donkey_plays.models.Player;
import com.example.donkey_plays.com.example.donkey_plays.services.PlayerService;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

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
        setContentView(R.layout.start);


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

               if(playerService.addPlayer(new Player(playerName, 0))){
                   numberOfPlayers.setText("Number of Players: " + playerService.getPlayers().size());
               }
            }
        });

        buttonPlayer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String playerName = player2Input.getEditText().getText().toString();

                if(playerService.addPlayer(new Player(playerName, 0))){
                    numberOfPlayers.setText("Number of Players: " + playerService.getPlayers().size());
                }
            }
        });

        buttonStartGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getBaseContext(), VoiceGameIntroductionActivity.class);
                startActivity(i);
            }
        });
    }


}
