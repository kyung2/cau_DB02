package com.example.eom.dbapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.example.eom.dbapp.Adapter.KidsCafeAdpater;
import com.example.eom.dbapp.Adapter.KidsCenterAdpater;
import com.example.eom.dbapp.vo.KidsCafeData;
import com.example.eom.dbapp.vo.KidsCenterData;

import java.util.ArrayList;

public class KidsCenterList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kidscenter_list);

        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.rv_kidscenter);
        ArrayList<KidsCenterData> arrayList = new ArrayList<>();
        for(int i=0 ;i<30;i++){
            arrayList.add(new KidsCenterData("temp","0108978512","경기도용인시시흥구기흥구","경기도","영이;ㄴ시",1234,122,45456.5,4565));
        }
        KidsCenterAdpater adapter = new KidsCenterAdpater(this,arrayList);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();


    }
}

