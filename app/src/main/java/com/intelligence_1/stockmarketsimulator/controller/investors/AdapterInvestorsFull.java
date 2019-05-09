package com.intelligence_1.stockmarketsimulator.controller.investors;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.intelligence_1.stockmarketsimulator.R;
import com.intelligence_1.stockmarketsimulator.model.investors.Investor;
import com.intelligence_1.stockmarketsimulator.model.utilities.Reports;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public class AdapterInvestorsFull extends RecyclerView.Adapter<AdapterInvestorsFull.ViewHolder>
implements Filterable {

    private Context context;
    private List<Investor> investorsFinal; // list of investor
    private List<Investor> investorsFinalFull; // copy of investor for filter purposes



    public AdapterInvestorsFull(Context context, List<Investor> investorsFinal) {

        this.context = context;
        this.investorsFinal = investorsFinal;

        // Create a copy of investor fo filter purposes
        this.investorsFinalFull = new ArrayList<>(investorsFinal);

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
        holder.companiesInvested.setText(Integer.toString(investorsFinal.get(position).getInvestorNumberOfBoughtShares()));
        holder.shares.setText(Integer.toString(investorsFinal.get(position).getShares().size()));

    }

    @Override
    public int getItemCount() {
        return investorsFinal.size();
    }

    /**
     * Filterable Interface implementation
     * @return a Filter
     */
    @Override
    public Filter getFilter() {
        return investorsFilter;
    }

    private Filter investorsFilter = new Filter() {

        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {

            ArrayList<Investor> filterList = new ArrayList<>();

            // if there is not input in search box then return the whole list
            if (charSequence == null || charSequence.length() == 0) {
                filterList.addAll(investorsFinalFull);
            } else {
                String filterPattern = charSequence.toString().toLowerCase().trim();

                for (Investor investor : investorsFinalFull) {

                    if (investor.getInvestorName().toLowerCase().contains(filterPattern) ||
                            (Integer.toString(investor.getInvestorID()). toLowerCase().contains(filterPattern))) {
                        filterList.add(investor);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filterList;

            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            investorsFinal.clear();
            investorsFinal.addAll((Collection<? extends Investor>) filterResults.values);
            notifyDataSetChanged();
        }
    };


    public class ViewHolder extends RecyclerView.ViewHolder {

        // Declare the Views
        private TextView id; // investor id
        private TextView name; // investor name
        private TextView shares; // shares
        private TextView companiesInvested; // number of companies invested in

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            // get the views
            id = itemView.findViewById(R.id.id_general_placeholder);
            name = itemView.findViewById(R.id.name_general_placeholder);
            shares = itemView.findViewById(R.id.initial_budget_general_placeholder);
            companiesInvested = itemView.findViewById(R.id.final_budget_general_placeholder);
        }
    }

    public void changeColor(ViewHolder holder, int i) {
        if (i == 1) {
            holder.id.setTextColor(Color.GREEN);
            holder.name.setTextColor(Color.GREEN);
            holder.companiesInvested.setTextColor(Color.GREEN);
            holder.shares.setTextColor(Color.GREEN);
        } else {
            holder.id.setTextColor(Color.RED);
            holder.name.setTextColor(Color.RED);
            holder.companiesInvested.setTextColor(Color.RED);
            holder.shares.setTextColor(Color.RED);
        }
    }
}
