package com.intelligence_1.stockmarketsimulator.controller;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.intelligence_1.stockmarketsimulator.R;
import com.intelligence_1.stockmarketsimulator.model.companies.Company;

import java.text.DecimalFormat;
import java.util.List;

public class AdapterCompanyResult extends RecyclerView.Adapter<AdapterCompanyResult.ViewHolder> {

    // Global Variable
    private Context context; // context for the views
    private List<Company> companies; // list of companies
    private DecimalFormat df = new DecimalFormat("0.0000"); // decimal format to show only two decimal places


    /**
     * Constructor of Class Company Adapter that takes as parameter:
     * @param context the context of where will be call
     * @param companies a list of investors to be display
     */
    public AdapterCompanyResult(Context context, List<Company> companies) {
        this.context = context;
        this.companies = companies;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_company_result, viewGroup, false);
        return new AdapterCompanyResult.ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //set the placeholders
        holder.id .setText(Integer.toString(companies.get(position).getCompanyID()));
        holder.name.setText(companies.get(position).getCompanyName());
        holder.shares.setText(Integer.toString(companies.get(position).getSharesSold()));
        holder.price.setText(df.format(companies.get(position).getSharePrice()));
        holder.capital.setText( "€" +
                df.format(companies.get(position).getCompanyNumberOfShares() *
                        companies.get(position).getSharePrice())
        );
        holder.sharesTotal.setText(Integer.toString(companies.get(position).getCompanyNumberOfShares()));
    }

    @Override
    public int getItemCount() {
        return companies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        /**
         * Create the Views and math the source with id that comes from the
         * adapter_company_result layout
         */
        private TextView id;
        private TextView name;
        private TextView shares;
        private TextView sharesTotal;
        private TextView price;
        private TextView capital;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.placeholder_company_id);
            name = itemView.findViewById(R.id.placeholder_company_name);
            shares = itemView.findViewById(R.id.placeholder_company_shares);
            price = itemView.findViewById(R.id.placeholder_companies_shares_price);
            capital = itemView.findViewById(R.id.placeholder_company_capital);
            sharesTotal = itemView.findViewById(R.id.placeholder_company_shares_total);
        }
    }
}
