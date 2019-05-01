package com.intelligence_1.stockmarketsimulator.controller;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.intelligence_1.stockmarketsimulator.R;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.Random;

/**
 * Stock Market Project
 * @author Asmer Bracho (2016328), Gabriel Oliveira (2016310), Miguelantonio Guerra (2016324)
 */
public class MainActivity extends AppCompatActivity {

    private GraphView graph; // view for graph
    private Button runSimulation; // view for button run_simulation
    private Button loadSimulation; // vie for button load_simulation

    private final String TAG = "MainActivity"; // String used for Debugging and printing Logs

    /**
     * On Create method is an override method create every time the activity is requested
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // get the views
        getViews();
        // Initialize the Graph
        initGraph(graph);

        // Handle the listeners
        handleListeners();
    }


    /**
     * Method that cointains the Graph to be shown in the main Screen
     * @param graph
     */
    public void initGraph(GraphView graph) {
        Random r = new Random();
        DataPoint[] dataSerie1 = new DataPoint[10];

        // first series
        for (int i=0; i<10 ; i++) {
            int j = r.nextInt(20);
            dataSerie1[i] = new DataPoint(i, j);
        }
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(dataSerie1);

        series.setAnimated(true);
        series.setColor(Color.GREEN);
        graph.addSeries(series);

        // second series
        DataPoint[] dataSerie2 = new DataPoint[10];
        for (int i=0; i<10 ; i++) {
            int j = r.nextInt(20);
            dataSerie2[i] = new DataPoint(i, j);
        }

        LineGraphSeries<DataPoint> series2 = new LineGraphSeries<>(dataSerie2);

        series2.setDrawBackground(true);
        series2.setColor(Color.argb(255, 19, 110, 106));
        series2.setBackgroundColor(Color.argb(100, 12, 178, 170));
        series2.setDrawDataPoints(true);
        series2.setAnimated(true);
        graph.addSeries(series2);

    }

    // Method to get the views from layout
    public void getViews() {
        graph = findViewById(R.id.graph);
        runSimulation = findViewById(R.id.run_simulation);
        loadSimulation = findViewById(R.id.load_simulation);
    }

    public void handleListeners() {

        // Load Simulations Click Listener
        loadSimulation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create a new Intent
                Intent loadSimulations = new Intent(getApplicationContext(), Results.class);
                startActivityForResult(loadSimulations, 0);
            }
        });

        runSimulation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent runSimulations = new Intent(getApplicationContext(), ExecutingSimulation.class);
                startActivity(runSimulations);
            }
        });
    }
}
