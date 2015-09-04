package com.example.dawid.dietalpha.controller;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.dawid.dietalpha.R;
import com.example.dawid.dietalpha.model.ItemData;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by Dawid on 2015-09-03.
 */
public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.ViewHolder> {
    private List<ItemData> mData;
    private LayoutInflater inflater;
    private Context ctx;
    private final int VIEW_FOOTER = 0;
    private final int VIEW_ITEM = 1;

    public MyRecyclerAdapter(List<ItemData> data, Context c){
        mData = data;
        ctx = c;
        inflater = LayoutInflater.from(ctx);
    }

    @Override
    public int getItemViewType(int position) {
        return (position == mData.size())? VIEW_FOOTER : VIEW_ITEM;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder res = null;
        if(viewType == VIEW_ITEM){
            View r = inflater.inflate(R.layout.list_item, parent, false);
            res = new ViewHolder(r, VIEW_ITEM);
        }
        else{
            Log.d("TAG", "FOOTER CREATED");
            View r = inflater.inflate(R.layout.list_footer, parent, false);
            res = new ViewHolder(r, VIEW_FOOTER);
        }
        return res;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if(position < mData.size()) {
            ItemData tmp = mData.get(position);
            Log.d("TAG", tmp.getName());
            holder.setName(tmp.getName());
            holder.setCarbo(tmp.getCarbo());
            holder.setFat(tmp.getFat());
            holder.setWeigth(tmp.getWeigth());
            holder.setCal(tmp.getCal());
        }
    }

    @Override
    public int getItemCount() {
        return mData.size() + 1;
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private TextView weigth;
        private TextView carbo;
        private TextView fat;
        private TextView cal;

        //footer
        private ImageButton ibAdd;

        public ViewHolder(View itemView, int viewType) {
            super(itemView);
            if (viewType == 1) {
                name = (TextView) itemView.findViewById(R.id.tvName);
                weigth = (TextView) itemView.findViewById(R.id.tvWeigth);
                carbo = (TextView) itemView.findViewById(R.id.tvCarbo);
                fat = (TextView) itemView.findViewById(R.id.tvFat);
                cal = (TextView) itemView.findViewById(R.id.tvCal);
            } else {
                ibAdd = (ImageButton) itemView.findViewById(R.id.ibAdd);
            }
        }

        public void setCal(String c){
            if(cal != null)cal.setText(c);
        }

        public void setName(String name) {
            if(this.name != null)this.name.setText(name);
        }

        public void setFat(String fat) {
            if(this.fat != null)this.fat.setText(fat);
        }

        public void setWeigth(String weigth) {
            if(this.weigth != null)this.weigth.setText(weigth);
        }

        public void setCarbo(String carbo) {
            if(this.carbo != null)this.carbo.setText(carbo);
        }
    }
}
