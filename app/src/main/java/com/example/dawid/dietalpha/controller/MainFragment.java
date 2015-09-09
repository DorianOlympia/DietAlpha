package com.example.dawid.dietalpha.controller;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dawid.dietalpha.R;
import com.example.dawid.dietalpha.model.ItemData;
import com.example.dawid.dietalpha.model.SubstituteAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Dawid on 2015-09-05.
 */
public class MainFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private List<ItemData> data = Collections.emptyList();
    private SubstituteAdapter mAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main, container, false);

        mAdapter = new SubstituteAdapter(data, getActivity());

        mRecyclerView = (RecyclerView)v.findViewById(R.id.productRecycler);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));data = new ArrayList<>();
        mRecyclerView.setAdapter(mAdapter);
        return v;
    }

    public void addData(ItemData it){
        data.add(it);
        mAdapter.setData(data);
        mAdapter.notifyDataSetChanged();
    }

    public void clearData(){
        data = new ArrayList<>();
        mAdapter.setData(data);
        mAdapter.notifyDataSetChanged();
    }


}
