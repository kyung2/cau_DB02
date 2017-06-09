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
        arrayList.add(new UrlAndName("/func1/"," 통학버스 운영하는 유치원"));
        arrayList.add(new UrlAndName("/func2/","안양시 유치원의 cctv개수"));
        arrayList.add(new UrlAndName("/func3/","1년 안의 유치원 사고사망"));
        arrayList.add(new UrlAndName("/func4/","유치원 선생님 수 내림차순"));
        arrayList.add(new UrlAndName("/func5/","유치원별 사고사망 수 오름차순"));
        arrayList.add(new UrlAndName("/func6/","댓글 수가 많은 유차원 내림차순"));
        arrayList.add(new UrlAndName("/func7/","평가가 높은 유치원 내림차순"));
        arrayList.add(new UrlAndName("/func8/","댓글 수가 많은 키즈카페 내림차순"));
        arrayList.add(new UrlAndName("/func9/","평가가 높은 키즈카페 내림차순"));
        arrayList.add(new UrlAndName("/func10/","평가가 낮은 키즈카페 오름차순"));
        arrayList.add(new UrlAndName("/func11/","평가가 낮은 유치원 오름차순"));
//        arrayList.add(new UrlAndName("/func12/","안양시 각 시설 2개씩 추천"));
        arrayList.add(new UrlAndName("/func13/","국공립 유치원 갯수"));
        arrayList.add(new UrlAndName("/func14/","교통사고가 가장 많이 난 곳"));
//        arrayList.add(new UrlAndName("/func15/","평가가 낮은 키즈카페 오름차순 중복"));
//        arrayList.add(new UrlAndName("/func16/"," 평가가 낮은 유치원 오름차순 중복"));
//        arrayList.add(new UrlAndName("/func17/"," 안양시 각 시설 2개씩 추천"));
        arrayList.add(new UrlAndName("/func18/","자격증이 2개 이상인 선생 list"));
        arrayList.add(new UrlAndName("/func19/","'장애', ' 특수교사' 를 가지고있는 선생이 있는 유치원"));
        arrayList.add(new UrlAndName("/func20/","정원이 50명 이상인 유치원의 선생 수"));

       FunctionListAdapter adapter = new FunctionListAdapter(this,arrayList);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }
}
