package com.saipc.tda.stopwatch;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

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
        setContentView(R.layout.stopWatchLayout);
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

    public void runTimer(View view) {
        TextView timeView = (TextView) findViewById(R.id.timeView);

        int hours = seconds / 3600;
        int minutes = (seconds % 3600)/ 60;
        int secs = seconds % 60;
        String time = String.format("%d:%2d:%2d", hours, minutes, secs);
        timeView.setText(time);

        if(running) {
            seconds++;
        }

    }


}
