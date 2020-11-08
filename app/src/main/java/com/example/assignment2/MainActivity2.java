package com.example.assignment2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity2 extends AppCompatActivity {
    int seconds = 0;
    boolean running = false;
    int begeningSeconds = 0;
    EditText minEdit, hrsEdit, secsEdit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Intent intent = getIntent();
        secsEdit = findViewById(R.id.secEditText);
        minEdit = findViewById(R.id.minEditText);
        hrsEdit = findViewById(R.id.hrsEditText);

        begeningSeconds = seconds;

        if (savedInstanceState != null) {
            seconds = savedInstanceState.getInt("seconds");
            running = savedInstanceState.getBoolean("running");
        }

        runTimer();


    }

    public void startOnClick(View view) {

        running = true;

    }

    public void pauseOnClick(View view) {
        running = false;
    }

    public void resetOnClick(View view) {
        running = false;
        seconds = begeningSeconds;

    }



    @Override
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putInt("seconds", seconds);
        bundle.putBoolean("running", running);
    }

    private void runTimer() {
        final TextView txtView = (TextView) findViewById(R.id.txtTimer);
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours = seconds / 3600;
                int minutes = seconds % 3600 / 60;
                int snds = seconds % 60;
                String time = String.format(Locale.getDefault(),
                        "%d:%02d:%02d", hours, minutes, snds);
                txtView.setText(time);
                if (running && seconds > 0) {
                    --seconds;
                }
                handler.postDelayed(this, 1000);
            }
        });
    }

    public void OnClickDone(View view) {
        if(secsEdit.getText().toString().isEmpty()||minEdit.getText().toString().isEmpty()||hrsEdit.getText().toString().isEmpty()){
            Toast.makeText(this,"Please fill all the fields",Toast.LENGTH_LONG).show();
            return;
        }
        int s = Integer.parseInt(secsEdit.getText().toString());
        int m = Integer.parseInt(minEdit.getText().toString());
        int h = Integer.parseInt(hrsEdit.getText().toString());
        seconds = s + m * 60 + 3600 * h;
        TextView txt = findViewById(R.id.txtTimer);
        running=false;

        String time = String.format(Locale.getDefault(),
                "%d:%02d:%02d", h, m, s);
        txt.setText(time);
    }
}