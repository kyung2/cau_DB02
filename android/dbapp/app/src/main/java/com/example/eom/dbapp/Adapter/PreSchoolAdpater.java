package com.example.eom.dbapp.Adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.eom.dbapp.R;
import com.example.eom.dbapp.vo.PreSchoolData;

import java.util.ArrayList;

/**
 * Created by dhtpr on 2017-06-05.
 *
 */

public class PreSchoolAdpater extends  RecyclerView.Adapter<PreSchoolAdpater.ViewHolder> {

    Context context;
    ArrayList<PreSchoolData> items;
    int item_layout;

    public PreSchoolAdpater(Context context, ArrayList<PreSchoolData> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_pre_school_list_item, parent, false);
        return new ViewHolder(v);
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final PreSchoolData item = items.get(position);
        holder.tv_name.setText(item.name);
        holder.tv_address.setText(item.address);
        holder.bt_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "전화버튼이 눌렸음", Toast.LENGTH_SHORT).show();
            }
        });
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return this.items.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv_name;
        TextView tv_address;
        ImageButton bt_call;
        CardView view;

        ViewHolder(View itemView) {
            super(itemView);
            tv_name = (TextView) itemView.findViewById(R.id.tv_pre_list_name);
            tv_address = (TextView) itemView.findViewById(R.id.tv_pre_list_address);
            bt_call = (ImageButton) itemView.findViewById(R.id.bt_pre_list_call);

            view = (CardView) itemView.findViewById(R.id.cv_preschool_list_item);
        }
    }

}