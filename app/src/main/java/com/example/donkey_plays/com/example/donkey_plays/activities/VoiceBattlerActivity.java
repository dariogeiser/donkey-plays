package com.example.donkey_plays.com.example.donkey_plays.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.donkey_plays.R;
import com.example.donkey_plays.com.example.donkey_plays.models.Game;
import com.example.donkey_plays.com.example.donkey_plays.models.GameState;

import java.util.concurrent.TimeUnit;

public class VoiceBattlerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getSupportActionBar().hide();


        setContentView(R.layout.voice_battler);


    }

    @Override
    protected void onResume() {
        super.onResume();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {

        }


        Intent i = new Intent(VoiceBattlerActivity.this, ResultActivity.class);
        startActivity(i);
    }
}
