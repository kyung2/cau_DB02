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
import com.example.eom.dbapp.vo.KidsCafeData;
import com.example.eom.dbapp.vo.TeacherData;

import java.util.ArrayList;

/**
 * Created by dhtpr on 2017-06-05.
 *
 */

public class TeacherAdpater extends  RecyclerView.Adapter<TeacherAdpater.ViewHolder> {

    Context context;
    ArrayList<TeacherData> items;
    int item_layout;

    public TeacherAdpater(Context context, ArrayList<TeacherData> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_teacher_list_item, parent, false);
        return new ViewHolder(v);
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final TeacherData item = items.get(position);
        holder.tv_name.setText(item.name);
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
        CardView view;

        ViewHolder(View itemView) {
            super(itemView);
            tv_name = (TextView) itemView.findViewById(R.id.tv_teacher_list_name);
            view = (CardView) itemView.findViewById(R.id.cv_teacher_list_item);
        }
    }

}