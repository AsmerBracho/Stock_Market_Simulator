package com.intelligence_1.stockmarketsimulator.controller.companies;

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

public class AdapterCompaniesFull extends RecyclerView.Adapter<AdapterCompaniesFull.ViewHolder> {

    private Context context;
    private List<Company> companiesFinal;
    private DecimalFormat df = new DecimalFormat("0.0000"); // decimal format to show only two decimal places

    public AdapterCompaniesFull(Context context, List<Company> companiesFinal) {

        this.context = context;
        this.companiesFinal = companiesFinal;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_companies_investor_full, viewGroup, false);
        return new AdapterCompaniesFull.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.id.setText(Integer.toString(companiesFinal.get(position).getCompanyID()));
        holder.name.setText(companiesFinal.get(position).getCompanyName());
        holder.finalShares.setText(Integer.toString(companiesFinal.get(position).getCompanyNumberOfShares()));
        holder.capital.setText("€" +
                df.format(companiesFinal.get(position).getCompanyNumberOfShares() *
                companiesFinal.get(position).getSharePrice()));
    }

    @Override
    public int getItemCount() {
        return companiesFinal.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        // Declare the Views
        private TextView id; // investor id
        private TextView name; // investor name
        private TextView capital; // investor initial budget
        private TextView finalShares; // investor number of shares bought

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            // get the views
            id = itemView.findViewById(R.id.id_general_placeholder);
            name = itemView.findViewById(R.id.name_general_placeholder);
            capital = itemView.findViewById(R.id.initial_budget_general_placeholder);
            finalShares = itemView.findViewById(R.id.final_budget_general_placeholder);
        }
    }
}
