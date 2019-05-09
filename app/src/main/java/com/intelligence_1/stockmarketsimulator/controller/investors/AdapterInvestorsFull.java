/**
 * Stock Market Project
 *
 * @authors Asmer Bracho (2016328),
 * Gabriel Oliveira (2016310),
 * Miguelantonio Guerra (2016324)
 */
package com.intelligence_1.stockmarketsimulator.controller.investors;

import android.content.Context;
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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * AdapterInvestor Full
 * contain the adapter to be applied to the list that contain all the investors
 */
public class AdapterInvestorsFull extends RecyclerView.Adapter<AdapterInvestorsFull.ViewHolder>
implements Filterable {

    private Context context; // application context
    private List<Investor> investorsFinal; // list of investor
    private List<Investor> investorsFinalFull; // copy of investor for filter purposes


    /**
     * Constructor of AdapterInvestorFull
     * @param context context
     * @param investorsFinal list of investors
     */
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

    /**
     * Create the Views and math the source with id that comes from the
     * adapter_investor_result layout
     */
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
}
