package com.example.dawid.dietalpha.controller;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.dawid.dietalpha.R;
import com.example.dawid.dietalpha.model.BasicProductAdapter;

/**
 * Created by Dawid on 2015-09-04.
 */
public class SelectProductActivity extends AppCompatActivity implements BasicProductAdapter.OnGroupSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_base);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.myLay, new SelectProductFragment(SelectProductFragment.RequestType.REQ_GROUPS));
        transaction.commit();

    }

    @Override
    public void onGroupSelected() {
        Toast.makeText(this, "Clicked", Toast.LENGTH_SHORT).show();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.myLay, new SelectProductFragment(SelectProductFragment.RequestType.REQ_PRODUCTS));
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
