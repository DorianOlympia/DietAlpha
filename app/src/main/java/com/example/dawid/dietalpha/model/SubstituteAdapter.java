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

import java.util.Collections;
import java.util.List;

/**
 * Created by Dawid on 2015-09-03.
 */
public class SubstituteAdapter extends RecyclerView.Adapter<SubstituteAdapter.ViewHolder> {
    private List<ItemData> mData;
    private LayoutInflater inflater;
    private Context ctx;
    private final int VIEW_FOOTER = 0;
    private final int VIEW_ITEM = 1;
    private OnAddButtonClickedListener mListener;

    public interface OnAddButtonClickedListener{
        void onAddNewItemButtonClicked();
    }

    public SubstituteAdapter(List<ItemData> data, Context c){
        mData = data;
        ctx = c;
        inflater = LayoutInflater.from(ctx);
        mListener = (OnAddButtonClickedListener) ctx;
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
            holder.setCarbo("wegle: " + String.valueOf(String.format("%.2f", tmp.getCarbo())) + "g");
            holder.setFat("tluszcze: " + String.valueOf(String.format("%.2f", tmp.getFat())) + "g");
            holder.setWeigth("ilosc: " + String.valueOf(String.format("%.2f", tmp.getWeigth())) + "g");
            holder.setCal("kalorie: " + String.valueOf(String.format("%.2f", tmp.getCal())) + "g");
        }
    }

    @Override
    public int getItemCount() {
        return mData.size() + 1;
    }

    public void setData(List<ItemData> data){
        mData = data;
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
                itemView.findViewById(R.id.layFooter).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mListener.onAddNewItemButtonClicked();
                    }
                });
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
