package com.example.dawid.dietalpha.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.dawid.dietalpha.R;
import com.example.dawid.dietalpha.model.BasicProductAdapter;

/**
 * Created by Dawid on 2015-09-04.
 */
public class SelectProductActivity extends AppCompatActivity implements BasicProductAdapter.OnGroupSelectedListener, ItemAmountDialog.NoticeDialogListener {

    private SelectProductFragment.RequestType type;
    private String gid = "N/A";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_base);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.myLay, new SelectProductFragment());
        transaction.commit();
        type = SelectProductFragment.RequestType.REQ_GROUPS;
    }

    @Override
    public void onItemSelected(String gid) {
        if(type.equals(SelectProductFragment.RequestType.REQ_GROUPS)) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.myLay, new SelectProductFragment(SelectProductFragment.RequestType.REQ_PRODUCTS, gid));
            transaction.addToBackStack(null);
            transaction.commit();
            type = SelectProductFragment.RequestType.REQ_PRODUCTS;
        }else if(MainActivity.getCurrentPick() == MainActivity.PICK_BASE){
            DialogFragment dialog = new ItemAmountDialog();
            dialog.show(getSupportFragmentManager(), "AMOUNT");
            this.gid = gid;
        }else{
            this.gid = gid;
            onDialogPositiveClick(100);
        }
    }

    @Override
    public void onDialogPositiveClick(int amount) {
        Intent res = getIntent();
        res.putExtra("id", gid);
        res.putExtra("amount", amount);
        setResult(RESULT_OK, res);
        finish();
    }

    @Override
    public void onDialogNegativeClick() {
        Toast.makeText(this, "Cancel button clicked", Toast.LENGTH_SHORT).show();
    }

}
