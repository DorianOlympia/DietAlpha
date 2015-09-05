package com.example.dawid.dietalpha.controller;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;
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
public class SelectFood extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private List<GroupJSONData> data = Collections.emptyList();
    private PGroupAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_base);


        mRecyclerView = (RecyclerView)findViewById(R.id.groupRecycler);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        data = new ArrayList<GroupJSONData>();
        for(int i = 0; i < 10; ++i) data.add(new GroupJSONData("name " + i, "GID"));

        mAdapter = new PGroupAdapter(data, this);
        mRecyclerView.setAdapter(mAdapter);


        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest requestJSON = new JsonObjectRequest(Request.Method.GET, "http://api.nal.usda.gov/ndb/list?format=json&lt=g&sort=n&api_key=fV2CXoFnfQ2x6eZCwIEinskdRaiPmj8WpqtjPKIx"
                ,null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                data = JSONParser.parseGroups(jsonObject);
                mAdapter.setData(data);
                mAdapter.notifyDataSetChanged();
                Toast.makeText(SelectFood.this, "Changes aquired", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(SelectFood.this, "An error occured", Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(requestJSON);
    }
}
