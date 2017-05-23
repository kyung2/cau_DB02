package com.example.eom.dbapp;


import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016-10-29.
 */
public class FunctionListAdapter extends RecyclerView.Adapter<FunctionListAdapter.ViewHolder>{

    Context context;
    ArrayList<String> items;
    int item_layout;

    public FunctionListAdapter(Context context, ArrayList<String> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_functions,parent,false);
//        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.place_list_item, null);
        return new ViewHolder(v);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBindViewHolder(ViewHolder holder,int position) {
        final String item = items.get(position);

        holder.tv_function_name.setText(item);

    }

    @Override
    public int getItemCount() {
        return this.items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv_function_name;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_function_name = (TextView) itemView.findViewById(R.id.tv1_layout_functions);
        }
    }

}