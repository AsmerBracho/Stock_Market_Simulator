/**
 * Stock Market Project
 *
 * @authors Asmer Bracho (2016328),
 * Gabriel Oliveira (2016310),
 * Miguelantonio Guerra (2016324)
 */
package com.intelligence_1.stockmarketsimulator.controller;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.intelligence_1.stockmarketsimulator.R;
import com.intelligence_1.stockmarketsimulator.controller.load.LoadSimulations;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.Random;


public class MainActivity extends AppCompatActivity implements PopUpDialog.ExampleDialogListener {

    private Context context; // context
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
        // context
        this.context = this;

        // get the views
        getViews();
        // Initialize the Graph
        initGraph(graph);

        // Handle the listeners
        handleListeners();

        // Check  if we are reloading this activity from save Record
        // if that is the case show the message
        String simulationSaved = getIntent().getStringExtra("simulationSaved");
        if ("SIMULATION_SAVE".equals(simulationSaved)) {
            // show a dialog
            recordSavedDialog();
        }

    }

    /**
     * Method that contains the Graph to be shown in the main screen
     * @param graph
     */
    public void initGraph(GraphView graph) {
        Random r = new Random();
        DataPoint[] dataSerie1 = new DataPoint[10];

        // first series
        for (int i = 0; i < 10; i++) {
            int j = r.nextInt(20);
            dataSerie1[i] = new DataPoint(i, j);
        }
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(dataSerie1);

        series.setAnimated(true);
        series.setColor(Color.GREEN);
        graph.addSeries(series);

        // second series
        DataPoint[] dataSerie2 = new DataPoint[10];
        for (int i = 0; i < 10; i++) {
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

    @Override
    public void onBackPressed() {
        finish();
    }

    public void recordSavedDialog() {
        new AlertDialog.Builder(context)
                .setTitle("SIMULATION SAVED")
                .setMessage("Your simulation have been successfully saved")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Dismiss
                    }
                })
                .show();
    }

    /**
     * Get the Views from layout
     */
    public void getViews() {
        graph = findViewById(R.id.graph);
        runSimulation = findViewById(R.id.run_simulation);
        loadSimulation = findViewById(R.id.load_simulation);
    }

    /**
     * Method that open dialog to input
     * companies number and investor number
     */
    public void openDialog() {
        PopUpDialog exampleDialog = new PopUpDialog();
        exampleDialog.show(getSupportFragmentManager(), "Start Trading");
    }

    /**
     * Handle the listener in the Activity
     * Load Simulation ans Run simulation listener
     */
    public void handleListeners() {

        // Load Simulations Click Listener
        loadSimulation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create a new Intent
                Intent loadSimulations = new Intent(getApplicationContext(), LoadSimulations.class);
                startActivityForResult(loadSimulations, 0);
            }
        });

        runSimulation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
//                Intent runSimulations = new Intent(getApplicationContext(), ExecutingSimulation.class);
//                startActivity(runSimulations);
            }
        });
    }

    @Override
    public void applyTexts(String username, String password) {

    }
}
