package com.intelligence_1.stockmarketsimulator.controller;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.intelligence_1.stockmarketsimulator.R;
import com.intelligence_1.stockmarketsimulator.model.companies.Company;
import com.intelligence_1.stockmarketsimulator.model.investors.Investor;

import java.util.ArrayList;
import java.util.List;

public class Results extends AppCompatActivity {

    private List<Investor> investors;
    private List<Company> companies;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bottom_nav_menu);

        // Get the List of investors and Companies
        investors = (ArrayList<Investor>) getIntent().getExtras().getSerializable("listOfInvestors");
        companies = (ArrayList<Company>) getIntent().getExtras().getSerializable("listOfCompanies");
        this.context = this;

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new FragmentResults(companies, investors)).commit();

        // handle the listeners in the apllication
        handleListeners();

        // Bottom Navigation View
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav_menu);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);

    }

    /**
     * Method that contains the Functionality for our Bottom Menu
     */
    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.botton_home:
                            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                                    new FragmentResults(companies, investors)).commit();
                            break;
                        case R.id.bottom_records:
                            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                                    new FragmentGraph(companies, investors)).commit();
                            break;
                    }
                    return true;
                }
            };

    /**
     * We will modify the onBAckPressed method to avoid changing the Activity and
     * lose the information
     */
    @Override
    public void onBackPressed() {
        exit();
    }

    /**
     * This method is called whrn the user either press back on the phone or
     * the exit button
     */
    public void exit() {

        // create a Dialog sreen to confirm if the user really want to exit
        new AlertDialog.Builder(context)
                .setTitle("EXIT")
                .setMessage("Are you sure you want to exit, without saving? Your Simulation will be deleted")
                .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // create Intent to go to Home Screen
                        Intent goHome = new Intent(context, MainActivity.class);
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

    public void handleListeners() {
        //--------------------------EXIT BOTTOM-------------------------//
        TextView exit = findViewById(R.id.exit);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exit();
            }
        });
    }

}
