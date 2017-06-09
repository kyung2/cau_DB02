package com.example.eom.dbapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.eom.dbapp.Adapter.FunctionListAdapter;
import com.example.eom.dbapp.DetailActivity.JustStringActivity;
import com.example.eom.dbapp.vo.UrlAndName;

import java.util.ArrayList;

public class FunctionsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_functions);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.rv_functions);
        ArrayList<UrlAndName> arrayList = new ArrayList<>();
        arrayList.add(new UrlAndName("/walfareService/gps/","주변 복지시설 보기"));
        arrayList.add(new UrlAndName("/kindergarten/near/","나랑 가까운 유치원 하나 보기"));
        for(int i=0 ;i<30;i++){
            arrayList.add(new UrlAndName("/walfareService/gps/","기능"+Integer.toString(i)));
        }

        //TODO 여기가 이제 현경이 너가 은정이한테 받은 url을 하나씩 놓는 부분임

//        arrayList.add(new UrlAndName("/walfareService/gps/","주변 복지시설 보기");
//        arrayList.add(new UrlAndName("/kindergarten/near/","나랑 가까운 유치원 하나 보기");
//        arrayList.add(new UrlAndName("/walfareService/gps/","기능"+Integer.toString(i)));
//        arrayList.add(new UrlAndName("/walfareService/gps/","기능"+Integer.toString(i)));
//        arrayList.add(new UrlAndName("/walfareService/gps/","기능"+Integer.toString(i)));
       FunctionListAdapter adapter = new FunctionListAdapter(this,arrayList);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }
}
