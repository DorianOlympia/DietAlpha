package com.example.dawid.dietalpha.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.dawid.dietalpha.R;
import com.example.dawid.dietalpha.model.ItemData;
import com.example.dawid.dietalpha.model.SubstituteAdapter;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private Toolbar tbr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = (RecyclerView)findViewById(R.id.productRecycler);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        ArrayList<ItemData> data = new ArrayList<>();
        for(int i = 0; i < 3; ++i){
            data.add(new ItemData("Nazwa " + i, "Waga: 9999 g", "Wegle: 9999 g", "Tluszcz: 9999 g", "9999 kcal"));
        }
        mRecyclerView.setAdapter(new SubstituteAdapter(data,this));

        //TOOLBAR
        tbr = (Toolbar)findViewById(R.id.toolbar);
        tbr.setTitle("Wyszukaj produkt bazowy: ");
        setSupportActionBar(tbr);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_search) {
            startActivity(new Intent(this, SelectFood.class));
        }

        return super.onOptionsItemSelected(item);
    }
}
