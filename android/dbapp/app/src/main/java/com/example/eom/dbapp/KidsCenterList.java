package com.example.eom.dbapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.example.eom.dbapp.Adapter.KidsCafeAdpater;
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
            arrayList.add(new KidsCenterData(1,"키즈까페"+i,"서울시 양천구 신정"+i+"동","0102252225"));
        }
        KidsCenterAdpater adapter = new KidsCafeAdpater(this,arrayList);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();


    }
}

}
