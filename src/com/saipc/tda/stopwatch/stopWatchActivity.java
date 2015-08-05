package com.saipc.tda.stopwatch;

import android.app.Activity;
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
    private boolean running = false;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stopwatch);
        runTimer();
    }

    public void onClickStart(View view) {
        this.running = true;
    }

    public void onClickStop(View view) {
        this.running = false;
    }

    public void onClickReset(View view) {
        this.running = false;
        this.seconds = 0;
    }

    private void runTimer() {
        TextView timeView = (TextView) findViewById(R.id.timeView);
        Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                if (running) {
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
