package com.intelligence_1.stockmarketsimulator.controller.companies;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import com.intelligence_1.stockmarketsimulator.R;
import com.intelligence_1.stockmarketsimulator.model.companies.Company;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class AdapterCompaniesFull extends RecyclerView.Adapter<AdapterCompaniesFull.ViewHolder>
implements Filterable {

    private Context context;
    private List<Company> companiesFinal;
    private List<Company> companiesFinalFull; // copy of companies for filter purposes
    private DecimalFormat df = new DecimalFormat("0.0000"); // decimal format to show only two decimal places

    public AdapterCompaniesFull(Context context, List<Company> companiesFinal) {

        this.context = context;
        this.companiesFinal = companiesFinal;

        // Create a copy of investor fo filter purposes
        this.companiesFinalFull = new ArrayList<>(companiesFinal);

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
        holder.finalShares.setText(Integer.toString(companiesFinal.get(position).getSharesSold()));
        holder.capital.setText("€" +
                df.format(companiesFinal.get(position).getCompanyNumberOfShares() *
                companiesFinal.get(position).getSharePrice()));
    }

    @Override
    public int getItemCount() {
        return companiesFinal.size();
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

            ArrayList<Company> filterList = new ArrayList<>();

            // if there is not input in search box then return the whole list
            if (charSequence == null || charSequence.length() == 0) {
                filterList.addAll(companiesFinalFull);
            } else {
                String filterPattern = charSequence.toString().toLowerCase().trim();

                for (Company company : companiesFinalFull) {

                    if (company.getCompanyName().toLowerCase().contains(filterPattern) ||
                            (Integer.toString(company.getCompanyID()). toLowerCase().contains(filterPattern))) {
                        filterList.add(company);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filterList;

            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            companiesFinal.clear();
            companiesFinal.addAll((Collection<? extends Company>) filterResults.values);
            notifyDataSetChanged();
        }
    };

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
