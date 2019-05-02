package com.intelligence_1.stockmarketsimulator.controller;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.intelligence_1.stockmarketsimulator.R;
import com.intelligence_1.stockmarketsimulator.model.companies.Company;
import com.intelligence_1.stockmarketsimulator.model.investors.Investor;
import com.intelligence_1.stockmarketsimulator.model.utilities.Reports;

import java.util.ArrayList;
import java.util.List;

public class Results extends AppCompatActivity {

    // Global Variables
    private CardView companyWithHighestCapital;
    private CardView companyWithLowestCapital;
    private CardView investorWithHighestShares;
    private CardView investorWithLowestShares;
    private CardView investorInvestedMostCompanies;
    private CardView investorInvestedLeastCompanies;

    private Context context;
    private TextView exit;
    private List<Investor> investors;
    private List<Company> companies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        context = this;

        // Get the List of investors and Companies
        investors = (ArrayList<Investor>) getIntent().getExtras().getSerializable("listOfInvestors");
        companies = (ArrayList<Company>) getIntent().getExtras().getSerializable("listOfCompanies");

        // get the views
        exit = findViewById(R.id.exit);
        investorWithHighestShares = findViewById(R.id.investor_higest_shares);
        investorWithLowestShares = findViewById(R.id.investor_less_shares);
        companyWithHighestCapital = findViewById(R.id.higest_capital);
        companyWithLowestCapital = findViewById(R.id.lowest_capital);
        investorInvestedMostCompanies = findViewById(R.id.investor_most_company);
        investorInvestedLeastCompanies = findViewById(R.id.investor_less_company);

        // set Listeners
        setListeners();
    }

    /**
     * We will modify the onBAckPressed method to avoid changing the Activity and
     * lose the information
     */
    @Override
    public void onBackPressed() {
        exit();
    }

    /**
     * Method that handle the listener for the different report and send to the Result Screen
     * filtering the necessary information by apply the Report class.
     */
    public void setListeners() {

        //--------------------------EXIT-------------------------//
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exit();
            }
        });

        //-----------COMPANY WITH HIGHEST CAPITAL --------------//
        companyWithHighestCapital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Take the list of results after the simulation and filter according to the report
                List<Company> filtered = Reports.getCompanyByCapital(companies, Reports.Capital.HIGHEST);
                // create an intent
                Intent seeResults = new Intent(getApplicationContext(), CompanyResult.class);
                // put  the new list in an Extra
                seeResults.putParcelableArrayListExtra("listOfCompanies", (ArrayList<? extends Parcelable>) filtered);
                // sent the title for next activity
                seeResults.putExtra("title", "Companies with Highest Capital");
                startActivity(seeResults);
            }
        });

        //-----------COMPANY WITH LOWEST CAPITAL --------------//
        companyWithLowestCapital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Filter the Lowest Capital
                List<Company> filtered = Reports.getCompanyByCapital(companies, Reports.Capital.LOWEST);
                // create an intent
                Intent seeResults = new Intent(getApplicationContext(), CompanyResult.class);
                // put  the new list in an Extra
                seeResults.putParcelableArrayListExtra("listOfCompanies", (ArrayList<? extends Parcelable>) filtered);
                // sent the title for next activity
                seeResults.putExtra("title", "Companies with Lowest Capital");
                startActivity(seeResults);
            }
        });

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
                // send companies as well to extract necessary results in the results
                seeResults.putParcelableArrayListExtra("listOfCompanies", (ArrayList<? extends Parcelable>) companies);

                // sent the title for next activity
                seeResults.putExtra("title", "Investor with Highest Shares");
                startActivity(seeResults);
            }
        });

        //-----------INVESTOR WITH LOWEST SHARES --------------//
        investorWithLowestShares.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Take the list of results after the simulation and filter according to the report
                List<Investor> filtered = Reports.getInvestorByShares(investors, Reports.Shares.LOWEST);
                // create an intent
                Intent seeResults = new Intent(getApplicationContext(), InvestorResult.class);
                // put  the new list in an Extra
                seeResults.putParcelableArrayListExtra("listOfInvestors", (ArrayList<? extends Parcelable>) filtered);
                // send companies as well to extract necessary results in the results
                seeResults.putParcelableArrayListExtra("listOfCompanies", (ArrayList<? extends Parcelable>) companies);

                // sent the title for next activity
                seeResults.putExtra("title", "Investor with Lowest Shares");
                startActivity(seeResults);
            }
        });

        //-----------INVESTOR THAT HAS INVESTED IN THE MOST COMPANIES --------------//
        investorInvestedMostCompanies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Take the list of results after the simulation and filter according to the report
                List<Investor> filtered = Reports.getInvestorByCompanyInvested(investors, Reports.Invested.MOST);
                // create an intent
                Intent seeResults = new Intent(getApplicationContext(), InvestorResult.class);
                // put  the new list in an Extra
                seeResults.putParcelableArrayListExtra("listOfInvestors", (ArrayList<? extends Parcelable>) filtered);
                // send companies as well to extract necessary results in the results
                seeResults.putParcelableArrayListExtra("listOfCompanies", (ArrayList<? extends Parcelable>) companies);

                // sent the title for next activity
                seeResults.putExtra("title", "Invested in the Most Companies");
                startActivity(seeResults);
            }
        });

        //-----------INVESTOR THAT HAS INVESTED IN THE LEAST COMPANIES --------------//
        investorInvestedLeastCompanies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Take the list of results after the simulation and filter according to the report
                List<Investor> filtered = Reports.getInvestorByCompanyInvested(investors, Reports.Invested.LEAST);
                // create an intent
                Intent seeResults = new Intent(getApplicationContext(), InvestorResult.class);
                // put  the new list in an Extra
                seeResults.putParcelableArrayListExtra("listOfInvestors", (ArrayList<? extends Parcelable>) filtered);
                // send companies as well to extract necessary results in the results
                seeResults.putParcelableArrayListExtra("listOfCompanies", (ArrayList<? extends Parcelable>) companies);

                // sent the title for next activity
                seeResults.putExtra("title", "Invested in the Least Companies");
                startActivity(seeResults);
            }
        });


    }

    /**
     * This methos is called whrn the user either press back on the phone or
     * the exit button
     */
    public void exit() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                // create a Dialog sreen to confirm if the user really want to exit
                new AlertDialog.Builder(context)
                        .setTitle("EXIT")
                        .setMessage("Are you sure you want to exit, without saving? Your Simulation will be deleted")
                        .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // create Intent to go to Home Screen
                                Intent goHome = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(goHome);
                            }
                        })
                        .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //Dismiss
                            }
                        })
                        .show();
            }
        });

    }
}
