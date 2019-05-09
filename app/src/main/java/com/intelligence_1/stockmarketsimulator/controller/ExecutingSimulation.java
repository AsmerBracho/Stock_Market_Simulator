/**
 * Stock Market Project
 * @authors Asmer Bracho (2016328),
 *          Gabriel Oliveira (2016310),
 *          Miguelantonio Guerra (2016324)
 */

package com.intelligence_1.stockmarketsimulator.controller;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.intelligence_1.stockmarketsimulator.Market;
import com.intelligence_1.stockmarketsimulator.MarketObserver;
import com.intelligence_1.stockmarketsimulator.R;
import com.intelligence_1.stockmarketsimulator.controller.result.Results;
import com.intelligence_1.stockmarketsimulator.model.companies.Company;
import com.intelligence_1.stockmarketsimulator.model.investors.Investor;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * ExecutingSimulation Class
 * Activity that calls the model and creates the simulation
 */
public class ExecutingSimulation extends AppCompatActivity {

    private final Handler mHandler = new Handler();
    private Runnable mTimer;

    // Variable for the Graph
    private double graphLastXValue = 5d;
    private LineGraphSeries<DataPoint> mSeries;
    private LineGraphSeries<DataPoint> mSeries2;
    // Lists
    private List<Investor> investorsResults; // list of investors
    private List<Company> companiesResults; // list of companies

    int investorsN;
    int companiesN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_executing_simulation);
        // get the view for the graph
        GraphView graphExecuting = findViewById(R.id.graph_executing);
        //init the graph
        initGraph(graphExecuting);

        final String investorNumber = getIntent().getStringExtra("investorsNumber");
        final String companyNumber = getIntent().getStringExtra("companyNumber");

        companiesN = Integer.parseInt(companyNumber);
        investorsN = Integer.parseInt(investorNumber);
        // The main Thread is running the views

        // We trigger a second Thread that runs simultaneously and execute the computing
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                // Start Trading
                Market market = new Market(companiesN,investorsN); // an Instance of the Market where we trade
                new MarketObserver(market); // create an observer that takes the market
                market.trade(); // start trading
                market.listAllCompanies();

                // get the list from the results
                investorsResults = market.getInvestors();
                companiesResults = market.getInactiveCompanies();

                // prepare the Intent to change Screens
                Intent goToResults = new Intent(getApplicationContext(), Results.class);
                goToResults.putParcelableArrayListExtra("listOfInvestors", (ArrayList<? extends Parcelable>) investorsResults);
                goToResults.putParcelableArrayListExtra("listOfCompanies", (ArrayList<? extends Parcelable>) companiesResults);

                // After the simulation is finish we open a new activity and catch the results for the simulation
                startActivityForResult(goToResults, 0);

                // reset Static Counter for IDs in investors and companies
                Investor.InvestorBuilder.resetStaticAuxiliaryID();
                Company.CompanyBuilder.resetStaticAuxiliaryID();
            }
        });
    }

    /**
     * Method that contain an animation of a Graph that
     * is part of the interaction and main thread of our simulation
     * @param graph
     */
    public void initGraph(GraphView graph) {
        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setMinX(0);
        graph.getViewport().setMaxX(4);
        graph.getGridLabelRenderer().setLabelVerticalWidth(100);

        // first mSeries is a line with background
        mSeries = new LineGraphSeries<>();
        mSeries.setDrawDataPoints(true);
        mSeries.setDrawBackground(true);
        graph.addSeries(mSeries);

        // Second mSeries is al plane line
        mSeries2 = new LineGraphSeries<>();
        mSeries2.setDrawDataPoints(true);
        mSeries2.setColor(Color.GREEN);
        graph.addSeries(mSeries2);
    }

    @Override
    public void onResume() {
        super.onResume();
        mTimer = new Runnable() { // create the runnable and start to draw
            @Override
            public void run() {
                graphLastXValue += 0.25d;
                mSeries.appendData(new DataPoint(graphLastXValue, getRandom()), true, 22);
                mSeries2.appendData(new DataPoint(graphLastXValue, getRandom()), true, 22);
                mHandler.postDelayed(this, 230);
            }
        };
        mHandler.postDelayed(mTimer,5);
    }

    public void onPause() {
        super.onPause();
        mHandler.removeCallbacks(mTimer);
    }

    double mLastRandom = 2;
    Random mRand = new Random();

    // Create a Method that generates a random number
    private double getRandom() {
        return mLastRandom += mRand.nextDouble()*0.5 - 0.25;
    }

    // Disable default function of onBackPressed so we make sure don't stop the simulation while running
    @Override
    public void onBackPressed() {
        // By doing nothing in this method we override the default function of the method
    }


}
