package com.intelligence_1.stockmarketsimulator.controller;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.intelligence_1.stockmarketsimulator.R;
import com.intelligence_1.stockmarketsimulator.model.investors.Investor;

import java.util.List;

public class AdapterInvestorResult extends RecyclerView.Adapter<AdapterInvestorResult.ViewHolder> {

    // Global Variable
    private Context context; // context for the views

    private List<Investor> investors;

    /**
     * Constructor of Class Company Adapter that takes as parameter:
     * @param context the context of where will be call
     * @param investors a list of investors to be display
     */
    public AdapterInvestorResult(Context context, List<Investor> investors) {
        this.context = context;
        this.investors = investors;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_investor_result, viewGroup, false);
        return new ViewHolder(view);
    }

    /**
     * This method manipulate and changes the data inside of our layout
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // set the placeholders
        holder.id.setText(Integer.toString(investors.get(position).getInvestorID()));
        holder.name.setText(investors.get(position).getInvestorName());
        holder.budget.setText(Double.toString(investors.get(position).getInvestorBudget()));
        holder.shares.setText(Integer.toString(investors.get(position).getInvestorNumberOfBoughtShares()));

//        int numberShares = 0;
//        // get value for companies by looping the haspMap
//        for (int i = 0; i < investors.get(position).getShares().size() ; i ++) {
//            //numberShares += investors.get(position).getShares()
//         }
    }

    @Override
    public int getItemCount() {
        return investors.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        /**
         * Create the Views and math the source with id that comes from the
         * Item_record layout
         */
        TextView id;
        TextView name;
        TextView budget;
        TextView companies;
        TextView shares;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.placeholder_investor_id);
            name = itemView.findViewById(R.id.placeholder_investor_name);
            budget = itemView.findViewById(R.id.placeholder_investor_budget);
            companies = itemView.findViewById(R.id.placeholder_investor_companies);
            shares = itemView.findViewById(R.id.placeholder_investor_shares);
        }
    }
}
