package com.intelligence_1.stockmarketsimulator.controller.investors;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.intelligence_1.stockmarketsimulator.R;
import com.intelligence_1.stockmarketsimulator.model.investors.Investor;

import java.util.List;

public class AdapterInvestorsFull extends RecyclerView.Adapter<AdapterInvestorsFull.ViewHolder> {

    private Context context;
    private List<Investor> investorsInitials;
    private List<Investor> investorsFinal;

    public AdapterInvestorsFull(Context context, List<Investor> investorsFinal) {

        this.context = context;
        this.investorsFinal = investorsFinal;

        Log.d("AdapterInvestorsFull", "ArraySize: " + investorsFinal.size());

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_companies_investor_full, viewGroup, false);
        return new AdapterInvestorsFull.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.id.setText(Integer.toString(investorsFinal.get(position).getInvestorID()));
        holder.name.setText(investorsFinal.get(position).getInvestorName());
        holder.finalShares.setText(Integer.toString(investorsFinal.get(position).getInvestorNumberOfBoughtShares()));
        holder.initialBudget.setText(Integer.toString(investorsFinal.get(position).getShares().size()));
    }

    @Override
    public int getItemCount() {
        return investorsFinal.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        // Declare the Views
        private TextView id; // investor id
        private TextView name; // investor name
        private TextView initialBudget; // investor initial budget
        private TextView finalShares; // investor number of shares bought

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            // get the views
            id = itemView.findViewById(R.id.id_general_placeholder);
            name = itemView.findViewById(R.id.name_general_placeholder);
            initialBudget = itemView.findViewById(R.id.initial_budget_general_placeholder);
            finalShares = itemView.findViewById(R.id.final_budget_general_placeholder);
        }
    }
}
