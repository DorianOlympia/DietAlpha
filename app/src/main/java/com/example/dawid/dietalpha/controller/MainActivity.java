package com.example.dawid.dietalpha.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.dawid.dietalpha.R;


public class MainActivity extends AppCompatActivity {

    private Toolbar tbr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


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
            startActivity(new Intent(this, SelectProductActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }
}
