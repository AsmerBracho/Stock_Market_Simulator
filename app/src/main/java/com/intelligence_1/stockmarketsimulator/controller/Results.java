package com.intelligence_1.stockmarketsimulator.controller;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.intelligence_1.stockmarketsimulator.R;
import com.intelligence_1.stockmarketsimulator.model.companies.Company;
import com.intelligence_1.stockmarketsimulator.model.investors.Investor;
import com.intelligence_1.stockmarketsimulator.model.utilities.Reports;

import java.util.ArrayList;
import java.util.List;

public class Results extends AppCompatActivity {

    // Global Variables
    private CardView investorWithHighestShares;

    private List<Investor> investors;
    private List<Company> companies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        // Get the List of investors and Companies
        investors = (ArrayList<Investor>) getIntent().getExtras().getSerializable("listOfInvestors");
        companies = (ArrayList<Company>) getIntent().getExtras().getSerializable("listOfCompanies");

        // get the views
        investorWithHighestShares = findViewById(R.id.investor_higest_shares);

        // set Listeners
        setListeners();
    }

    private void setListeners() {
        //-----------INVESTOR WITH HIGHEST SHARES --------------//
        investorWithHighestShares.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Take the list of results after the simulation and filter according to the report
                List<Investor> filtered = Reports.getInvestorByShares(investors, Reports.Shares.HIGHEST);
                // create an intent
                Intent seeResults = new Intent(getApplicationContext(), InvestorResult.class);
                // put  the new list in an Extra
                seeResults.putParcelableArrayListExtra("listOfInvestors", (ArrayList<? extends Parcelable>) filtered);
                // sent the title for next activity
                seeResults.putExtra("title", "Investor with Highest Shares");
                startActivity(seeResults);


            }
        });
    }
}
