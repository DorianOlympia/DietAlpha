package com.example.dawid.dietalpha.model;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dawid.dietalpha.R;

import java.util.List;

/**
 * Created by Dawid on 2015-09-04.
 */
public class BasicProductAdapter extends RecyclerView.Adapter<BasicProductAdapter.ViewHolder> {
    private List<JsonData> mData;
    private LayoutInflater inflater;
    private Context ctx;
    OnGroupSelectedListener listener;

    public interface OnGroupSelectedListener{
        public void onGroupSelected();
    }

    public BasicProductAdapter(List<JsonData> data, Context c){
        mData = data;
        ctx = c;
        inflater = LayoutInflater.from(ctx);
        listener = (OnGroupSelectedListener)c;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.list_item_basic, parent, false);
        ViewHolder res = new ViewHolder(v);
        return res;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.setName(mData.get(position).getGname());
    }

    public void setData(List<JsonData> d){
        mData = d;
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView name;

        public ViewHolder(View itemView) {
            super(itemView);
            name = (TextView)itemView.findViewById(R.id.tvBasicItemName);
            itemView.findViewById(R.id.layoutSimpleItem).setOnClickListener(this);
        }
        public void setName(String nm){name.setText(nm);}

        @Override
        public void onClick(View v) {
            listener.onGroupSelected();
        }
    }
}
