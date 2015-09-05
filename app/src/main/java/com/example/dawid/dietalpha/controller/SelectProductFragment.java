package com.example.dawid.dietalpha.controller;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.dawid.dietalpha.R;
import com.example.dawid.dietalpha.model.JsonData;
import com.example.dawid.dietalpha.model.JSONParser;
import com.example.dawid.dietalpha.model.BasicProductAdapter;

import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.Collections;
import java.util.List;

/**
 * Created by Dawid on 2015-09-05.
 */
public class SelectProductFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private List<JsonData> data = Collections.emptyList();
    private BasicProductAdapter mAdapter;
    private RequestType type;
    private String groupId = "N/A";
    private TextView searchTitle;

    public enum RequestType{
        REQ_GROUPS,
        REQ_PRODUCTS
    }

    public SelectProductFragment(){
        super();
        type = RequestType.REQ_GROUPS;
    }

    public SelectProductFragment(RequestType x, String groupId){
        super();
        type = x;
        this.groupId = groupId;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_select_product, container, false);

        mRecyclerView = (RecyclerView)v.findViewById(R.id.groupRecycler);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mAdapter = new BasicProductAdapter(data, getActivity());
        mRecyclerView.setAdapter(mAdapter);

        searchTitle = (TextView) v.findViewById(R.id.tvSearchTitle);

        if(data.isEmpty()) {
            switch(type){
                case REQ_GROUPS:
                    requestGroups();
                    searchTitle.setText("Select group of products: ");
                    break;
                case REQ_PRODUCTS:
                    requestProducts();
                    searchTitle.setText("Select particular product: ");
                    break;
            }
        }
        return v;
    }

    private void requestProducts() {
        Log.d("TAG", "VolleyRequest sent");
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        JsonObjectRequest requestJSON = new JsonObjectRequest(Request.Method.GET, "http://api.nal.usda.gov/ndb/nutrients/?format=json&api_key=fV2CXoFnfQ2x6eZCwIEinskdRaiPmj8WpqtjPKIx&max=1500&nutrients=205&fg=" + groupId
                , null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                data = JSONParser.parseProducts(jsonObject);
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
    }

    private void requestGroups() {
        Log.d("TAG", "VolleyRequest sent");
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        JsonObjectRequest requestJSON = new JsonObjectRequest(Request.Method.GET, "http://api.nal.usda.gov/ndb/list?format=json&lt=g&sort=n&api_key=fV2CXoFnfQ2x6eZCwIEinskdRaiPmj8WpqtjPKIx"
                , null, new Response.Listener<JSONObject>() {
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
    }
}
