package com.example.eom.dbapp.Adapter;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.eom.dbapp.DetailActivity.DetailChildCareCenterActivity;
import com.example.eom.dbapp.DetailActivity.DetailElmSchoolActivity;
import com.example.eom.dbapp.DetailActivity.DetailHospitalActivity;
import com.example.eom.dbapp.DetailActivity.DetailPlayFacilityActivity;
import com.example.eom.dbapp.DetailActivity.DetailSafeAreaActivity;
import com.example.eom.dbapp.DetailActivity.DetailSafeAreaActivity;
import com.example.eom.dbapp.ListActivity.ChildCareCenterListActivity;
import com.example.eom.dbapp.ListActivity.ElmSchoolListActivity;
import com.example.eom.dbapp.ListActivity.HospitalListActivity;
import com.example.eom.dbapp.ListActivity.PlayFacilityListActivity;
import com.example.eom.dbapp.ListActivity.SafeAreaListActivity;
import com.example.eom.dbapp.ListActivity.TrafficAccidentAreaListActivity;
import com.example.eom.dbapp.ListActivity.WalfareServiceListActivity;
import com.example.eom.dbapp.R;
import com.example.eom.dbapp.vo.IdAndString;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016-10-29.
 */
public class IdAndStringListAdapter extends RecyclerView.Adapter<IdAndStringListAdapter.ViewHolder>{

    Context context;
    ArrayList<IdAndString> items;
    int item_layout;

    public IdAndStringListAdapter(Context context, ArrayList<IdAndString> items) {
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
        final IdAndString item = items.get(position);
        holder.tv_function_name.setText(item.text);
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(item.type==IdAndString.ChildCareCenterListActivity){
                    context.startActivity(new Intent(context,DetailChildCareCenterActivity.class).putExtra("id",item.id));
                }else if(item.type==IdAndString.ElmSchoolListActivity){
                    context.startActivity(new Intent(context,DetailElmSchoolActivity.class).putExtra("id",item.id));
                }else if(item.type==IdAndString.HospitalListActivity){
                    context.startActivity(new Intent(context,DetailHospitalActivity.class).putExtra("id",item.id));

                }else if(item.type==IdAndString.PlayFacilityListActivity){
                    context.startActivity(new Intent(context,DetailPlayFacilityActivity.class).putExtra("id",item.id));

                }else if(item.type==IdAndString.SafeAreaListActivity){
                    context.startActivity(new Intent(context,DetailSafeAreaActivity.class).putExtra("id",item.id));

                }else if(item.type==IdAndString.TrafficAccidentAreaListActivity){
                    context.startActivity(new Intent(context,TrafficAccidentAreaListActivity.class).putExtra("id",item.id));

                }else if(item.type==IdAndString.WalfareServiceListActivity){
                    context.startActivity(new Intent(context,WalfareServiceListActivity.class).putExtra("id",item.id));

                }
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
            this.view= itemView;
        }
    }

}