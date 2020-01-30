package com.example.donkey_plays.com.example.donkey_plays.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.TextView;

import com.example.donkey_plays.R;
import com.example.donkey_plays.com.example.donkey_plays.models.Game;
import com.example.donkey_plays.com.example.donkey_plays.models.GameState;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class VoiceBattlerActivity extends AppCompatActivity {


    MediaRecorder mRecorder;
    TextView voice;
    TextView timeLeft;
    List<Double> allVoices = new ArrayList();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getSupportActionBar().hide();


        setContentView(R.layout.voice_battler);

        voice = findViewById(R.id.voice);
        timeLeft = findViewById(R.id.timeLeft);

    }

    @Override
    protected void onStart() {
        super.onStart();


        mRecorder = new MediaRecorder();
        mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        mRecorder.setOutputFile("/dev/null");
        try {
            mRecorder.prepare();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        mRecorder.start();


        new CountDownTimer(10000, 500) {
            public void onTick(long millisUntilFinished) {
                if (mRecorder != null) {
                    double amplitude = mRecorder.getMaxAmplitude();
                    double dB = 20.0 * Math.log10(amplitude);
                    allVoices.add(dB);
                    if(dB >= 0) {
                        voice.setText(Math.round(dB) + " dB");
                    }
                    timeLeft.setText("Time Left: " + millisUntilFinished / 1000 + " seconds");
                }

            }

            public void onFinish() {
                mRecorder.stop();
                double maxVoice = Collections.max(allVoices);
                Game game = GameState.getGame();
                System.out.println(game.getCurrentMinigame().getCurrentPlayer().getName());
                game.getCurrentMinigame().addStanding(game.getCurrentMinigame().getCurrentPlayer().getName(), maxVoice);
                GameState.setGame(game);
                Intent i = new Intent(VoiceBattlerActivity.this, ResultActivity.class);
                System.out.println(maxVoice);
                i.putExtra("value", Double.valueOf(Math.round(maxVoice)) + " dB");
                startActivity(i);
            }
        }.start();


    }


}














