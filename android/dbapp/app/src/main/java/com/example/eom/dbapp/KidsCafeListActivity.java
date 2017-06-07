package com.example.eom.dbapp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import com.example.eom.dbapp.Adapter.KidsCafeAdapter;
import com.example.eom.dbapp.network.KidsCafeByGPSTask;
import com.example.eom.dbapp.network.KidsCenterByGPSTask;
import com.example.eom.dbapp.vo.KidsCafeData;
import com.example.eom.dbapp.vo.KidsCenterData;

import java.util.ArrayList;

public class KidsCafeListActivity extends AppCompatActivity {
    KidsCafeAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kidscafe_list);
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.rv_kidscafe);
        final ArrayList<KidsCafeData> arrayList = new ArrayList<>();
         adapter = new KidsCafeAdapter(this,arrayList);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        SharedPreferences sp = getSharedPreferences(MySharedPreferences.NAME,0);
        Double latitude = Double.parseDouble(sp.getString(MySharedPreferences.USER_LATITUDE,"35"));
        Double longitude = Double.parseDouble(sp.getString(MySharedPreferences.USER_LONGITUDE,"123"));

        new KidsCafeByGPSTask(latitude,longitude){
            @Override
            protected void onPostExecute(ArrayList<KidsCafeData> kidsCenterDatas) {
                super.onPostExecute(kidsCenterDatas);
                arrayList.clear();
                arrayList.addAll(kidsCenterDatas);
                adapter.notifyDataSetChanged();
            }
        }.execute("");
    }
}
