package com.example.dawid.dietalpha.controller;

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
public class SelectProductActivity extends AppCompatActivity implements BasicProductAdapter.OnGroupSelectedListener {

    private SelectProductFragment.RequestType type;

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
        }else{
            DialogFragment dialog = new ItemAmountDialog();
            dialog.show(getSupportFragmentManager(), "AMOUNT");
        }
    }
}
