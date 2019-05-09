/**
 * Stock Market Project
 * @authors Asmer Bracho (2016328),
 *          Gabriel Oliveira (2016310),
 *          Miguelantonio Guerra (2016324)
 */
package com.intelligence_1.stockmarketsimulator.controller;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import com.intelligence_1.stockmarketsimulator.R;

/**
 * Initial Splash Screen that inflates the Loader Layout
 * in order to show the progress while loading the App
 */
public class InitialSplashScreen extends AppCompatActivity {

    private final static int TIME_OUT = 1500; // time for loading

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.initial_splash_screen);

        // Create a Handler and run the Tread inside with the given delay
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
