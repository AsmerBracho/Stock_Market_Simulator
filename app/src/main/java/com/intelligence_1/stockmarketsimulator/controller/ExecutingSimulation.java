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
import com.intelligence_1.stockmarketsimulator.model.companies.Company;
import com.intelligence_1.stockmarketsimulator.model.investors.Investor;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ExecutingSimulation extends AppCompatActivity {

    private final Handler mHandler = new Handler();
    private Runnable mTimer;
    private double graphLastXValue = 5d;
    private LineGraphSeries<DataPoint> mSeries;
    private LineGraphSeries<DataPoint> mSeries2;

    private List<Investor> investorsResults;
    private List<Company> companiesResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_executing_simulation);
        // get the view
        GraphView graphExecuting = (GraphView) findViewById(R.id.graph_executing);
        //init the graph
        initGraph(graphExecuting);

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                // Start Trading
                Market market = new Market();
                new MarketObserver(market);
                market.trade();
                market.listAllCompanies();

                // get the list from the results
                investorsResults = market.getInvestors();
                companiesResults = market.getInactiveCompanies();

                Intent goToResults = new Intent(getApplicationContext(), Results.class);

                goToResults.putParcelableArrayListExtra("listOfInvestors", (ArrayList<? extends Parcelable>) investorsResults);
                goToResults.putParcelableArrayListExtra("listOfCompanies", (ArrayList<? extends Parcelable>) companiesResults);

                startActivityForResult(goToResults, 0);

                // reset Static Counter for IDs in investors and companies
                Investor.InvestorBuilder.resetStaticAuxiliaryID();
                Company.CompanyBuilder.resetStaticAuxiliaryID();
            }
        });


    }



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
        mTimer = new Runnable() {
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
