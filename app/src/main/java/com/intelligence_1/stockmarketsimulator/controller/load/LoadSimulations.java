package com.intelligence_1.stockmarketsimulator.controller.load;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.apollographql.apollo.ApolloCall;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.exception.ApolloException;
import com.intelligence_1.stockmarketsimulator.GetAllSimulationsQuery;
import com.intelligence_1.stockmarketsimulator.GetSpecificSimulationQuery;
import com.intelligence_1.stockmarketsimulator.R;
import com.intelligence_1.stockmarketsimulator.controller.StockMarketDAO;
import com.intelligence_1.stockmarketsimulator.controller.result.Results;
import com.intelligence_1.stockmarketsimulator.model.companies.Company;
import com.intelligence_1.stockmarketsimulator.model.investors.Investor;
import com.intelligence_1.stockmarketsimulator.type.ShareInput;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LoadSimulations extends AppCompatActivity {

    // Our list of simulations
    private List<GetAllSimulationsQuery.GetAllSimulation> simulations = new ArrayList<>();


    // validation variable
    private String desisionTaker = null;

    // Simulation ID (String cause it comes as an intent
    String simulationID; // simulation id to be retrieve if exits

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.load_simulation_fragment);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Inflate and Initialize the loader
        getSupportFragmentManager().beginTransaction().replace(R.id.loader_fragment_container,
                new LoaderLoadSimulationFragment()).commit();

        // get the decisionTaker variable
        desisionTaker = getIntent().getStringExtra("desisionTaker");
        // get the simulationID
        simulationID = getIntent().getStringExtra("simulationID");

        Log.d("LoadSimulation", "simulationID" + simulationID);

        if (desisionTaker == null) {
            getAllSimulation();
        } else if ("RETRIEVING_A_SIMULATION".equals(desisionTaker)) {
            retrieveSimulation(Integer.parseInt(simulationID));
        }
    }

    private void retrieveSimulation(int simulationid) {
        // get simulation
        StockMarketDAO.getConnection().query(GetSpecificSimulationQuery.builder()
                .id(simulationid).build()).enqueue(new ApolloCall.Callback<GetSpecificSimulationQuery.Data>() {
            @Override
            public void onResponse(@NotNull Response<GetSpecificSimulationQuery.Data> response) {
                // create arrays lists for our records
                ArrayList<Investor> investors = new ArrayList<>();
                ArrayList<Company> companies = new ArrayList<>();

                // fill companies array
                for (int i = 0 ; i< response.data().simulation().companies().size() ; i++) {
                    companies.add(new Company.CompanyBuilder(
                            response.data().simulation().companies().get(i).name(),
                            response.data().simulation().companies().get(i).numShares(),
                            response.data().simulation().companies().get(i).price()
                    ).build());
                    companies.get(companies.size() - 1).setSharePrice(
                            response.data().simulation().companies().get(i).price());
                    companies.get(companies.size() - 1).setSharesSold(
                            response.data().simulation().companies().get(i).sharesSold());
                }

                // fill investors array
                for (int i = 0 ; i< response.data().simulation().investors().size() ; i++) {
                    investors.add(new Investor.InvestorBuilder(
                            response.data().simulation().investors().get(i).name(),
                            response.data().simulation().investors().get(i).budget()
                    ).build());
                    investors.get(investors.size() -1).setInvestorNumberOfBoughtShares(
                            response.data().simulation().investors().get(i).sharesBought());

                    // Get investor id
                    int investorID = response.data().simulation().investors().get(i).id();
                    HashMap<Integer, Integer> map = new HashMap<>();
                    for (GetSpecificSimulationQuery.Share si: response.data().simulation().shares()) {
                        if (investorID == si.investorId()) {
                            map.put(si.companyId(), si.sharesTraded());
                        }
                    }
                    // set the list of shares
                    investors.get(investors.size() -1).setShares(map);
                }

                // Start a result activity with the results we just got
                Intent goToResults = new Intent(getApplicationContext(), Results.class);
                goToResults.putParcelableArrayListExtra("listOfInvestors", (ArrayList<? extends Parcelable>) investors);
                goToResults.putParcelableArrayListExtra("listOfCompanies", (ArrayList<? extends Parcelable>) companies);

                goToResults.putExtra("disableSaveBtn", "DISABLE_SAVE_BTN");
                // After the simulation is finish we open a new activity and catch the results for the simulation
                startActivityForResult(goToResults, 0);

                // reset Static Counter for IDs in investors and companies
                Investor.InvestorBuilder.resetStaticAuxiliaryID();
                Company.CompanyBuilder.resetStaticAuxiliaryID();


            }

            @Override
            public void onFailure(@NotNull ApolloException e) {

            }
        });
    }

    /**
     * Method that calls our StockMarketDAO client to get a conection
     * and request info to database
     */
    public void getAllSimulation() {
        // Request Info to Data Base
        StockMarketDAO.getConnection().query(GetAllSimulationsQuery.builder().build())
                .enqueue(new ApolloCall.Callback<GetAllSimulationsQuery.Data>() {
                    @Override
                    public void onResponse(@NotNull Response<GetAllSimulationsQuery.Data> response) {
                        // add all the simulations in our list
                        simulations.addAll(response.data().getAllSimulations());

                        // Check if there is any simulation to beshown
                        if (simulations.size() != 0) {
                            // Go to the list of Simulations
                            getSupportFragmentManager().beginTransaction().replace(R.id.loader_fragment_container,
                                    new LoadSimulationFragment(simulations)).commit();
                            // Otherwise show no simulations screen
                        } else {
                            getSupportFragmentManager().beginTransaction().replace(R.id.loader_fragment_container,
                                    new NoRecordsAvailablesFragment()).commit();
                        }
                    }

                    @Override
                    public void onFailure(@NotNull ApolloException e) {

                    }
                });
    }
}
