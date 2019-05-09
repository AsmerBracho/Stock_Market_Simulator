package com.intelligence_1.stockmarketsimulator.controller.load;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        holder.id.setText(Integer.toString(simulations.get(position).id()));
        holder.name.setText(simulations.get(position).name());

        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loadThisSimulation = new Intent(context, LoadSimulations.class);
                loadThisSimulation.putExtra("simulationID", Integer.toString(simulations.get(position).id()));
                loadThisSimulation.putExtra("desisionTaker", "RETRIEVING_A_SIMULATION");
                context.startActivity(loadThisSimulation);
            }
        });
    }

    @Override
    public int getItemCount() {
        return simulations.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        // Define the Views
        private TextView id; // simulation id
        private TextView name; // simulation name
        private CardView parent; // simulation container

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // Get the views
            id = itemView.findViewById(R.id.simulation_id);
            name = itemView.findViewById(R.id.simulation_name);
            parent = itemView.findViewById(R.id.simulation_parent);
        }

    }
}
