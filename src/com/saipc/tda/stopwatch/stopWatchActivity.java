package com.saipc.tda.stopwatch;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.os.Handler;

public class stopWatchActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
    // Private variables
    private int seconds = 0;
    private boolean isRunning = false;
    private boolean wasRunning = false;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stopwatch);
        if (savedInstanceState != null) {
            this.seconds = savedInstanceState.getInt("seconds");
            this.isRunning = savedInstanceState.getBoolean("isRunning");
            this.wasRunning = savedInstanceState.getBoolean("wasRunning");
        }
        runTimer();
    }

    @Override
    public void onConfigurationChanged(Configuration configuration) {
        // do nothing
    }

    @Override
    protected void onStop() {
        super.onStop();
        this.isRunning = false;
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (this.wasRunning) {
            this.isRunning = true;
        }
    }


    @Override
    protected void onPause() {
        super.onPause();
        // same as onStop, but set wasRunning.
        this.wasRunning = this.isRunning;
        this.isRunning = false;
    }

    @Override
    protected void onResume() {
        super.onResume();
        // same as onStart
        if (this.wasRunning) {
            this.isRunning = true;
        }
    }


    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putInt("seconds", this.seconds);
        savedInstanceState.putBoolean("isRunning", this.isRunning);
        savedInstanceState.putBoolean("wasRunning", this.wasRunning);
    }
    public void onClickStart(View view) {
        this.isRunning = true;
    }

    public void onClickStop(View view) {
        this.isRunning = false;
    }

    public void onClickReset(View view) {
        this.isRunning = false;
        this.seconds = 0;
        TextView timeView = (TextView) findViewById(R.id.timeView);
        String time = String.format("%02d:%02d:%02d", 0, 0, 0);
        timeView.setText(time);
    }

    private void runTimer() {
        TextView timeView = (TextView) findViewById(R.id.timeView);
        Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                if (isRunning) {
                    seconds++;
                }

                int hours = seconds / 3600;
                int minutes = (seconds % 3600) / 60;
                int secs = seconds % 60;
                String time = String.format("%02d:%02d:%02d", hours, minutes, secs);
                timeView.setText(time);

                handler.postDelayed(this, 999);
            }
        });

    }


}
