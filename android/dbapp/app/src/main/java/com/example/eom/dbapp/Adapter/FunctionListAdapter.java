package com.example.eom.dbapp.Adapter;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.eom.dbapp.DetailActivity.JustStringActivity;
import com.example.eom.dbapp.MainActivity;
import com.example.eom.dbapp.R;
import com.example.eom.dbapp.vo.UrlAndName;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016-10-29.
 */
public class FunctionListAdapter extends RecyclerView.Adapter<FunctionListAdapter.ViewHolder>{

    Context context;
    ArrayList<UrlAndName> items;
    int item_layout;

    public FunctionListAdapter(Context context, ArrayList<UrlAndName> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_functions,parent,false);
//        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.place_list_item, null);
        return new ViewHolder(v);
    }


    @Override
    public void onBindViewHolder(ViewHolder holder,int position) {
        final UrlAndName item = items.get(position);
        holder.tv_function_name.setText(item.name);
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, JustStringActivity.class);
                intent.putExtra("url",item.url);
                context.startActivity(intent);
                Log.d("으어",item.url);
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv_function_name;
        View view;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_function_name = (TextView) itemView.findViewById(R.id.tv1_layout_functions);
            view = itemView;
        }
    }

}