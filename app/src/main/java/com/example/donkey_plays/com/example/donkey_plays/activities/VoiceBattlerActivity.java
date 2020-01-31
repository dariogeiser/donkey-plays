package com.example.donkey_plays.com.example.donkey_plays.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.view.View;
import android.widget.TextView;

import com.example.donkey_plays.R;
import com.example.donkey_plays.com.example.donkey_plays.models.Game;
import com.example.donkey_plays.com.example.donkey_plays.models.GameState;
import com.example.donkey_plays.com.example.donkey_plays.services.MusicService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class VoiceBattlerActivity extends AppCompatActivity {


    MediaRecorder mRecorder;
    TextView voice;
    TextView timeLeft;
    List<Double> allVoices = new ArrayList();
    Vibrator vibrator;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getSupportActionBar().hide();
        hideSystemUI();

        setContentView(R.layout.voice_battler);

        Intent svc = new Intent(this, MusicService.class);
        stopService(svc);

        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

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
                    if (dB >= 0) {
                        voice.setText(Math.round(dB) + " dB");
                    }
                    timeLeft.setText("Time Left: " + millisUntilFinished / 1000 + " seconds");
                }
                if (millisUntilFinished < 2000) {
                    vibrator.vibrate(2000);
                }
            }


            public void onFinish() {
                mRecorder.stop();
                double maxVoice = Collections.max(allVoices);
                Game game = GameState.getGame();
                System.out.println(game.getCurrentMinigame().getCurrentPlayer().getName());
                game.getCurrentMinigame().addStanding(game.getCurrentMinigame().getCurrentPlayer().getName(), maxVoice);
                GameState.setGame(game);
                Intent svc = new Intent(VoiceBattlerActivity.this, MusicService.class);
                startService(svc);
                Intent i = new Intent(VoiceBattlerActivity.this, ResultActivity.class);
                System.out.println(maxVoice);
                i.putExtra("value", Double.valueOf(Math.round(maxVoice)) + " dB");
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














