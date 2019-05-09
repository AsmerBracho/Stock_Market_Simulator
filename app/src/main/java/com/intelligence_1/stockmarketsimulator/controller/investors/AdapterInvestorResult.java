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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.intelligence_1.stockmarketsimulator.R;
import com.intelligence_1.stockmarketsimulator.model.companies.Company;
import com.intelligence_1.stockmarketsimulator.model.investors.Investor;

import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class AdapterInvestorResult extends RecyclerView.Adapter<AdapterInvestorResult.ViewHolder> {

    // Global Variable
    private Context context; // context for the views
    private List<Investor> investors; // list of investors
    private List<Company> companies; // list of companies
    private DecimalFormat df = new DecimalFormat("0.0000"); // decimal format to show only two decimal places

    /**
     * Constructor of Class Investor Adapter that takes as parameter:
     *
     * @param context   the context of where will be call
     * @param investors a list of investors to be display
     * @param companies a list of companies to get info from
     */
    public AdapterInvestorResult(Context context, List<Investor> investors, List<Company> companies) {
        this.context = context;
        this.investors = investors;
        this.companies = companies;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_investor_result, viewGroup, false);

        return new ViewHolder(view);
    }

    /**
     * This method manipulates and changes the data inside of our layout
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // set the placeholders
        holder.id.setText(Integer.toString(investors.get(position).getInvestorID()));
        holder.name.setText(investors.get(position).getInvestorName());
        holder.budget.setText("€" + df.format(investors.get(position).getInvestorBudget()));
        holder.shares.setText(Integer.toString(investors.get(position).getInvestorNumberOfBoughtShares()));
        holder.companiesInvested.setText(Integer.toString(investors.get(position).getShares().size()));

        // Initially the capital is equal to the remaining budget of investor
        double investorCapital = investors.get(position).getInvestorBudget();
        Map<Integer, Integer> mp = investors.get(position).getShares();
        // get value for companies by looping the haspMap
        Iterator it = mp.entrySet().iterator();

        double sharePrice = 0;
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            int companyId = (int) pair.getKey();

            for (Company c : companies) {
                if (c.getCompanyID() == companyId) {
                    sharePrice = c.getSharePrice();
                }
            }
            investorCapital += sharePrice * ((int) pair.getValue());
            it.remove(); // avoids a ConcurrentModificationException
        }
        holder.capital.setText("€" + df.format(investorCapital));

    }

    @Override
    public int getItemCount() {
        return investors.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        /**
         * Create the Views and math the source with id that comes from the
         * adapter_investor_result layout
         */
        private TextView id; // investor id
        private TextView name; // investor name
        private TextView budget; // investor budget
        private TextView companiesInvested; // investor companies invested in
        private TextView shares; // number of shares bough
        private TextView capital; // investor capital

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.placeholder_investor_id);
            name = itemView.findViewById(R.id.placeholder_investor_name);
            budget = itemView.findViewById(R.id.placeholder_investor_budget);
            companiesInvested = itemView.findViewById(R.id.placeholder_investor_companies);
            shares = itemView.findViewById(R.id.placeholder_investor_shares);
            capital = itemView.findViewById(R.id.placeholder_investor_capital);
        }
    }
}
