package com.example.dawid.dietalpha.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.dawid.dietalpha.R;

import org.w3c.dom.Text;


public class MainActivity extends AppCompatActivity {

    public static final int PICK_BASE = 1;
    public static final int PICK_COUN = 2;
    private Toolbar tbr;

    private TextView tvBaseName;
    private TextView tvBaseAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //BASE VIEW
        tvBaseName = (TextView)findViewById(R.id.tvBaseName);
        tvBaseAmount = (TextView)findViewById(R.id.tvBaseAmount);

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
            startActivityForResult(new Intent(this, SelectProductActivity.class), PICK_BASE);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == MainActivity.PICK_BASE) {
            Log.d("SUCCESS", "CODE OK");
            if (resultCode == RESULT_OK) {
                Log.d("SUCCESS", data.getStringExtra("id"));
                Log.d("SUCCESS", String.valueOf(data.getIntExtra("amount", -1)));
                tvBaseName.setText(data.getStringExtra("id"));
                tvBaseAmount.setText(String.valueOf(data.getIntExtra("amount", -1)));
            }
        }
    }
}
