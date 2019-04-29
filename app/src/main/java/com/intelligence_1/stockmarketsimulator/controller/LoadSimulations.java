package com.intelligence_1.stockmarketsimulator.controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.intelligence_1.stockmarketsimulator.R;

public class LoadSimulations extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_simulations);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
