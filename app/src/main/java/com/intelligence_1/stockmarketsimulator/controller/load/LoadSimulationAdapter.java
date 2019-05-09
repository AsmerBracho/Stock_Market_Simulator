package com.intelligence_1.stockmarketsimulator.controller.load;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.intelligence_1.stockmarketsimulator.GetAllSimulationsQuery;
import com.intelligence_1.stockmarketsimulator.R;

import java.util.List;

public class LoadSimulationAdapter extends RecyclerView.Adapter<LoadSimulationAdapter.ViewHolder> {

    private Context context; // context for the views
    private List<GetAllSimulationsQuery.GetAllSimulation> simulations;

    public LoadSimulationAdapter (Context context, List<GetAllSimulationsQuery.GetAllSimulation> simulations) {
        this.context = context;
        this.simulations = simulations;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_simulation, viewGroup, false);

        return new LoadSimulationAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return simulations.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
