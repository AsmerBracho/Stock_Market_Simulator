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


public class ExampleDialog extends AppCompatDialogFragment {
    private EditText editTextCompanies;
    private EditText editTextInvestors;
    private ExampleDialogListener listener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_dialog, null);

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