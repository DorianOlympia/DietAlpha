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

/**
 * Created by Dawid on 2015-09-05.
 */
public class MainFragment extends Fragment {

    private RecyclerView mRecyclerView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main, container, false);

        mRecyclerView = (RecyclerView)v.findViewById(R.id.productRecycler);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        ArrayList<ItemData> data = new ArrayList<>();
        for(int i = 0; i < 3; ++i){
            data.add(new ItemData("Nazwa " + i, "Waga: 9999 g", "Wegle: 9999 g", "Tluszcz: 9999 g", "9999 kcal"));
        }
        mRecyclerView.setAdapter(new SubstituteAdapter(data,getActivity()));

        return v;
    }
}
