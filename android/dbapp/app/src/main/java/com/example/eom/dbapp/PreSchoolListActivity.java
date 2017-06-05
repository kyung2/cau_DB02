package com.example.eom.dbapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.eom.dbapp.Adapter.FunctionListAdapter;
import com.example.eom.dbapp.Adapter.PreSchoolAdpater;
import com.example.eom.dbapp.vo.PreSchoolData;

import java.util.ArrayList;

public class PreSchoolListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preshcool_list);
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.rv_preschools);
        ArrayList<PreSchoolData> arrayList = new ArrayList<>();
        for(int i=0 ;i<30;i++){

            arrayList.add(new PreSchoolData("유치원"+i,"서울시 양천구 신정"+i+"동","0102252225"));
        }
        PreSchoolAdpater adapter = new PreSchoolAdpater(this,arrayList);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }
}
