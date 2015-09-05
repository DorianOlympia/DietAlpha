package com.example.dawid.dietalpha.controller;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.dawid.dietalpha.R;
import com.example.dawid.dietalpha.model.GroupJSONData;
import com.example.dawid.dietalpha.model.JSONParser;
import com.example.dawid.dietalpha.model.PGroupAdapter;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Dawid on 2015-09-04.
 */
public class SelectBaseActivity extends AppCompatActivity implements PGroupAdapter.OnGroupSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_base);
    }

    @Override
    public void onGroupSelected() {
        Toast.makeText(this, "Group selected", Toast.LENGTH_SHORT).show();
    }
}
