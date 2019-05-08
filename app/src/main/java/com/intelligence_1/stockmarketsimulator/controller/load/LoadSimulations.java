package com.intelligence_1.stockmarketsimulator.controller.load;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.intelligence_1.stockmarketsimulator.R;

public class LoadSimulations extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.load_simulation_fragment);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Inflate and Initialize the loader
        getSupportFragmentManager().beginTransaction().replace(R.id.loader_fragment_container,
                new LoaderLoadSimulationFragment()).commit();

        // Request Info to Data Base

    }
}
