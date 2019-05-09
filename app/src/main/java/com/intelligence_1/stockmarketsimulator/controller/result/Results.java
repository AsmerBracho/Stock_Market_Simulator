package com.intelligence_1.stockmarketsimulator.controller.result;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.apollographql.apollo.ApolloCall;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.exception.ApolloException;
import com.intelligence_1.stockmarketsimulator.AddSimulationMutation;
import com.intelligence_1.stockmarketsimulator.R;
import com.intelligence_1.stockmarketsimulator.controller.ExecutingSimulation;
import com.intelligence_1.stockmarketsimulator.controller.MainActivity;
import com.intelligence_1.stockmarketsimulator.controller.StockMarketDAO;
import com.intelligence_1.stockmarketsimulator.controller.companies.FragmentCompanies;
import com.intelligence_1.stockmarketsimulator.controller.graphql.InvestorGraphQL;
import com.intelligence_1.stockmarketsimulator.controller.investors.FragmentInvestors;
import com.intelligence_1.stockmarketsimulator.controller.result.FragmentGraph;
import com.intelligence_1.stockmarketsimulator.controller.result.FragmentResults;
import com.intelligence_1.stockmarketsimulator.model.companies.Company;
import com.intelligence_1.stockmarketsimulator.model.investors.Investor;
import com.intelligence_1.stockmarketsimulator.type.CompanyInput;
import com.intelligence_1.stockmarketsimulator.type.InvestorInput;
import com.intelligence_1.stockmarketsimulator.type.ShareInput;

import org.jetbrains.annotations.NotNull;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Results extends AppCompatActivity {

    private ArrayList<Investor> investors;
    private ArrayList<Company> companies;
    private Context context;

    private ProgressDialog progressDialog;

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

        // handle the listeners in the application
        handleListeners();

        // Bottom Navigation View
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav_menu);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);

        // create progress Dialogo
        progressDialog = new ProgressDialog(context);
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
                        case R.id.botton_investors:
                            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                                    new FragmentInvestors(investors)).commit();
                            break;
                        case R.id.botton_companies:
                            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                                    new FragmentCompanies(companies)).commit();
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
        // create a Dialog screen to confirm if the user really want to exit
        new AlertDialog.Builder(context)
                .setTitle("EXIT")
                .setMessage("Are you sure you want to exit, without saving? Your Simulation will be deleted")
                .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // create Intent to go to Home Screen
                        Intent goHome = new Intent(context, MainActivity.class);
                        goHome.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
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

        //--------------------------SAVE SIMULATION-------------------------//
        TextView saveSimulation = findViewById(R.id.save_simulation);
        saveSimulation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveSimulation();
            }
        });
    }

    public void saveSimulation() {
        progressDialog.setTitle("Saving");
        progressDialog.setMessage("Please wait while your record are saved...");
        progressDialog.show();
        // before saving we need to create the lists of object to be saved in the database
        List<InvestorInput> investorInput = new ArrayList<>();
        List<CompanyInput> companyInput = new ArrayList<>();
        // for shares we will create a List and put records in that and
        // when done we will converted to array to pass it to the query
        List<ShareInput> sharesInput = new ArrayList<>();

        Log.d("Results", "shares size" + sharesInput.size());
        for (int i = 0; i < investors.size(); i++) {
            investorInput.add(InvestorInput.builder()
                    .id(investors.get(i).getInvestorID())
                    .name(investors.get(i).getInvestorName())
                    .budget(investors.get(i).getInvestorBudget())
                    .sharesBought(investors.get(i).getInvestorNumberOfBoughtShares())
                    .build());

            Map<Integer, Integer> mp = investors.get(i).getShares();
            Iterator it = mp.entrySet().iterator();
            while (it.hasNext()) {

                Map.Entry pair = (Map.Entry) it.next();
                int companyId = (int) pair.getKey();
                int sharesTraded = (int) pair.getValue();
                sharesInput.add(ShareInput.builder()
                        .investorId(investors.get(i).getInvestorID())
                        .companyId(companyId)
                        .sharesTraded(sharesTraded)
                        .build()
                );
            }
            Log.d("Results", "shares size" + sharesInput.get(i).companyId());
        }

        for (int i = 0; i < companies.size(); i++) {
            companyInput.add(CompanyInput.builder()
                    .id(companies.get(i).getCompanyID())
                    .name(companies.get(i).getCompanyName())
                    .numShares(companies.get(i).getCompanyNumberOfShares())
                    .sharesSold(companies.get(i).getSharesSold())
                    .build());
        }

        for (int i = 0; i < sharesInput.size(); i++) {
            Log.d("Results: ","Investor ID: " + sharesInput.get(i).investorId() + " - Company ID: " + sharesInput.get(i).companyId() + " - Shares Trade: " + sharesInput.get(i).sharesTraded());
        }

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();

        // Create a Connection and call the query
        StockMarketDAO.getConnection().mutate(AddSimulationMutation.builder()
                .name("Simulation: " + dateFormat.format(date))
                .companies(companyInput)
                .investors(investorInput)
                .shares(sharesInput)
                .build()).enqueue(new ApolloCall.Callback<AddSimulationMutation.Data>() {
            @Override
            public void onResponse(@NotNull Response<AddSimulationMutation.Data> response) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        progressDialog.dismiss();
                        Intent goHome = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(goHome);
                    }
                });
            }

            @Override
            public void onFailure(@NotNull ApolloException e) {

            }
        });
    }
}
