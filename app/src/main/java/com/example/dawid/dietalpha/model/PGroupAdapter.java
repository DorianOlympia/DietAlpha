package com.example.dawid.dietalpha.model;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.dawid.dietalpha.R;

import java.util.List;

/**
 * Created by Dawid on 2015-09-04.
 */
public class PGroupAdapter extends RecyclerView.Adapter<PGroupAdapter.ViewHolder> {
    private List<GroupJSONData> mData;
    private LayoutInflater inflater;
    private Context ctx;
    private final int VIEW_FOOTER = 0;
    private final int VIEW_ITEM = 1;

    public PGroupAdapter(List<GroupJSONData> data, Context c){
        mData = data;
        ctx = c;
        inflater = LayoutInflater.from(ctx);
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.basic_list_item, parent, false);
        ViewHolder res = new ViewHolder(v);
        return res;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.setName(mData.get(position).getGname());
    }

    public void setData(List<GroupJSONData> d){
        mData = d;
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView name;

        public ViewHolder(View itemView) {
            super(itemView);
            name = (TextView)itemView.findViewById(R.id.tvBasicItemName);
        }
        public void setName(String nm){name.setText(nm);}
    }
}
