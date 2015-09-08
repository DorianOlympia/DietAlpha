package com.example.dawid.dietalpha.controller;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.NumberPicker;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.dawid.dietalpha.R;

import org.w3c.dom.Text;

/**
 * Created by Dawid on 2015-09-08.
 */
public class ItemAmountDialog extends DialogFragment {
    private SeekBar amount;
    private TextView tvAmount;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View v = getActivity().getLayoutInflater().inflate(R.layout.dialog_amount, null);
        builder.setView(v);

        tvAmount = (TextView)v.findViewById(R.id.tvAmount);
        amount = (SeekBar)v.findViewById(R.id.amountFood);
        amount.setMax(100);
        amount.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tvAmount.setText(String.valueOf(progress * 5));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });


        builder.setPositiveButton("Zatwierdz", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.d("TAG", "POSITIVE BUTTON CLICKED");
            }
        });

        builder.setNegativeButton("Anuluj", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.d("TAG", "NEGATIVE BUTTON CLICKED");
            }
        });

        return builder.create();
    }
}
