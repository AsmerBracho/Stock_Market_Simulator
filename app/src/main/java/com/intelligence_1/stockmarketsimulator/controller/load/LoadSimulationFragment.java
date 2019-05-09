/**
 * Stock Market Project
 *
 * @authors Asmer Bracho (2016328),
 * Gabriel Oliveira (2016310),
 * Miguelantonio Guerra (2016324)
 */
package com.intelligence_1.stockmarketsimulator.controller.load;
import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.intelligence_1.stockmarketsimulator.GetAllSimulationsQuery;
import com.intelligence_1.stockmarketsimulator.R;
import java.util.List;

public class LoadSimulationFragment extends Fragment {

    // Global Variables
    private Context context;
    private RecyclerView fragmentSimulationList;
    // Our list of simulations
    private List<GetAllSimulationsQuery.GetAllSimulation> simulations;

    // Default Constructor
    public LoadSimulationFragment() {}

    /**
     * Fragment LoadSimulation
     * @param simulations a list of simulations to be displayed
     */
    @SuppressLint("ValidFragment")
    public LoadSimulationFragment(List<GetAllSimulationsQuery.GetAllSimulation> simulations) {
        this.simulations = simulations;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_load_simulation, container, false);

        context = getContext();

        // get the view for recycler view
        fragmentSimulationList = view.findViewById(R.id.fragment_simulation_list);

        // creates an adapter
        LoadSimulationAdapter loadSimulationAdapter = new LoadSimulationAdapter(context, simulations);

        // set the adapter

        // set the adapter
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        fragmentSimulationList.setLayoutManager(layoutManager);
        fragmentSimulationList.setAdapter(loadSimulationAdapter);

        return view;
    }
}
