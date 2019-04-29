package com.intelligence_1.stockmarketsimulator.controller;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.intelligence_1.stockmarketsimulator.R;

public class InitialSplashScreen extends AppCompatActivity {

    private final static int TIME_OUT = 1500;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.initial_splash_screen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent goHome = new Intent (getApplicationContext(), MainActivity.class);
                startActivity(goHome);
                finish();
            }
        },TIME_OUT);

    }
}
