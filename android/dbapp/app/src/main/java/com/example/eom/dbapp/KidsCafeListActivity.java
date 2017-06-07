package com.example.eom.dbapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import com.example.eom.dbapp.Adapter.KidsCafeAdapter;
import com.example.eom.dbapp.vo.KidsCafeData;

import java.util.ArrayList;

public class KidsCafeListActivity extends AppCompatActivity {
    KidsCafeAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kidscafe_list);
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.rv_kidscafe);
        ArrayList<KidsCafeData> arrayList = new ArrayList<>();
         adapter = new KidsCafeAdapter(this,arrayList);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }
}
