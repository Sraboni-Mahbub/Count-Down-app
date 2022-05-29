package com.example.counterapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    private int mInterval = 100;
    private Handler mHandler;
    private int id = 2019160138;
    private TextView timer;
    private Button start;
    private Button reset;
    private Button pause;
    private Button exit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mHandler = new Handler();
        //startRepeatingTask();
        start = findViewById(R.id.start);
        reset = findViewById(R.id.reset);
        pause = findViewById(R.id.pause);
        exit = findViewById(R.id.exit);
        start.setOnClickListener(view -> startRepeatingTask());
        pause.setOnClickListener(view -> stopRepeatingTask());
        reset.setOnClickListener(view -> resetTimer());
        exit.setOnClickListener(view -> finish());

    }

    @Override
    public void onDestroy() {

        super.onDestroy();
        stopRepeatingTask();

    }
    Runnable mStatusChecker = new Runnable() {
        @Override
        public void run() {
            try {
               updateStatus();
            } finally {

                mHandler.postDelayed(mStatusChecker, mInterval);
            }
        }
    };
    void startRepeatingTask() {
        mStatusChecker.run();
    }

    void updateStatus(){
        if(id>0){
            id--;
        }
        timer = findViewById(R.id.timer);
        timer.setText(String.valueOf(id));

    }
    void stopRepeatingTask() {
        mHandler.removeCallbacks(mStatusChecker);
    }

    void resetTimer(){
        id=2019160138;
        stopRepeatingTask();
        timer = findViewById(R.id.timer);
        timer.setText(String.valueOf(id));
    }
}