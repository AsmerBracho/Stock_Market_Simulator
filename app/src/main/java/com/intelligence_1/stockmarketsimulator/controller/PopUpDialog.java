/**
 * Stock Market Project
 *
 * @authors Asmer Bracho (2016328),
 * Gabriel Oliveira (2016310),
 * Miguelantonio Guerra (2016324)
 */
package com.intelligence_1.stockmarketsimulator.controller;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.intelligence_1.stockmarketsimulator.R;

/**
 * PopUp Dialog class
 * Creates a Pop Up that contains the views for the user to input
 * the number of companies and investors to be created
 */
public class PopUpDialog extends AppCompatDialogFragment {
    // views
    private EditText editTextCompanies; // field for company input
    private EditText editTextInvestors; // field for investor input
    private ExampleDialogListener listener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_dialog, null);

        // set a view
        builder.setView(view)
                .setTitle("Set Up the Values")
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String companies = editTextCompanies.getText().toString();
                        String investors = editTextInvestors.getText().toString();
                        Intent runSimulations = new Intent(getContext(), ExecutingSimulation.class);
                        // put the extras to be send to the executingSimulation activity and initialize the lists
                        runSimulations.putExtra("investorsNumber", investors);
                        runSimulations.putExtra("companyNumber", companies);
                        startActivity(runSimulations);
                    }
                });

        editTextCompanies = view.findViewById(R.id.edit_companies);
        editTextInvestors = view.findViewById(R.id.edit_investors);

        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            listener = (ExampleDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() +
                    "must implement ExampleDialogListener");
        }
    }

    public interface ExampleDialogListener {
        void applyTexts(String username, String password);
    }
}