package com.intelligence_1.stockmarketsimulator.controller.load;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.apollographql.apollo.ApolloCall;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.exception.ApolloException;
import com.intelligence_1.stockmarketsimulator.GetAllSimulationsQuery;
import com.intelligence_1.stockmarketsimulator.R;
import com.intelligence_1.stockmarketsimulator.controller.StockMarketDAO;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class LoadSimulations extends AppCompatActivity {

    // Our list of simulations
    private List<GetAllSimulationsQuery.GetAllSimulation> simulations = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.load_simulation_fragment);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Inflate and Initialize the loader
        getSupportFragmentManager().beginTransaction().replace(R.id.loader_fragment_container,
                new LoaderLoadSimulationFragment()).commit();

        // Request Info to Data Base
        StockMarketDAO.getConnection().query(GetAllSimulationsQuery.builder().build())
                .enqueue(new ApolloCall.Callback<GetAllSimulationsQuery.Data>() {
                    @Override
                    public void onResponse(@NotNull Response<GetAllSimulationsQuery.Data> response) {
                        // add all the simulations in our list
                        simulations.addAll(response.data().getAllSimulations());

                        // Check if there is any simulation to beshown
                        if (simulations.size() !=0) {
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
