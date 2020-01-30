package com.example.donkey_plays.com.example.donkey_plays.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.preference.PreferenceManager;
import android.widget.TextView;
import android.widget.Toast;

import com.example.donkey_plays.R;
import com.example.donkey_plays.com.example.donkey_plays.models.Game;
import com.example.donkey_plays.com.example.donkey_plays.models.GameState;

import java.util.Collections;

public class StepCounterActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor stepCounter;

    private boolean firstTime = true;
    private float oldSteps = 0;

    private float newSteps = 0;

    TextView steps;
    TextView timeLeft;

    private static final String Initial_Count_Key = "FootStepInitialCount";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getSupportActionBar().hide();
        setContentView(R.layout.step_counter);

        //int startingStepCount = prefs.getInt(Initial_Count_Key, -1);

        final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        if(!prefs.contains(Initial_Count_Key)){
            System.out.println("here");
            SharedPreferences.Editor editor = prefs.edit();
            editor.putInt(Initial_Count_Key, 0);
            editor.commit();
        }

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        steps = findViewById(R.id.steps);
        timeLeft = findViewById(R.id.timeLeft);
    }

    @Override
    protected void onResume() {
        super.onResume();
        stepCounter = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        System.out.println(stepCounter.getName());
        sensorManager.registerListener(this, stepCounter, SensorManager.SENSOR_DELAY_NORMAL);
        System.out.println("start");

        if(stepCounter == null){
            Toast.makeText(this, "Sensor not found!" ,Toast.LENGTH_SHORT).show();
        }

        new CountDownTimer(20000, 500) {
            public void onTick(long millisUntilFinished) {
                timeLeft.setText("Time Left: " + millisUntilFinished / 1000 + " seconds");
            }

            public void onFinish() {
                Game game = GameState.getGame();
                game.getCurrentMinigame().addStanding(game.getCurrentMinigame().getCurrentPlayer().getName(), (double) newSteps);
                GameState.setGame(game);
                Intent i = new Intent(StepCounterActivity.this, ResultActivity.class);
                System.out.println(newSteps);
                i.putExtra("value", newSteps + " Steps");
                startActivity(i);
            }
        }.start();
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if(firstTime){
            oldSteps = event.values[0];
            newSteps = 0;
            firstTime = false;
        } else {
            newSteps = event.values[0] - oldSteps;
        }


        steps.setText(String.valueOf(newSteps) + " Steps");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        sensorManager.unregisterListener(this);
    }
}
