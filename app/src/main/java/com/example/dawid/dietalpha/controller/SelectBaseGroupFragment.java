package com.example.dawid.dietalpha.controller;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
 * Created by Dawid on 2015-09-05.
 */
public class SelectBaseGroupFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private List<GroupJSONData> data = Collections.emptyList();
    private PGroupAdapter mAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_select_base, container, false);

        mRecyclerView = (RecyclerView)v.findViewById(R.id.groupRecycler);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        data = new ArrayList<GroupJSONData>();
        for(int i = 0; i < 10; ++i) data.add(new GroupJSONData("name " + i, "GID"));

        mAdapter = new PGroupAdapter(data, getActivity());
        mRecyclerView.setAdapter(mAdapter);


        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        JsonObjectRequest requestJSON = new JsonObjectRequest(Request.Method.GET, "http://api.nal.usda.gov/ndb/list?format=json&lt=g&sort=n&api_key=fV2CXoFnfQ2x6eZCwIEinskdRaiPmj8WpqtjPKIx"
                ,null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                data = JSONParser.parseGroups(jsonObject);
                mAdapter.setData(data);
                mAdapter.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(getActivity(), "An error occured", Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(requestJSON);
        return v;
    }
}
