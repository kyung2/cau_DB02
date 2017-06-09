package com.example.eom.dbapp.ListActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.eom.dbapp.Adapter.KidsCenterAdpater;
import com.example.eom.dbapp.MySharedPreferences;
import com.example.eom.dbapp.R;
import com.example.eom.dbapp.network.KidsCenterByGPSTask;
import com.example.eom.dbapp.network.KidsCenterDetailTask;
import com.example.eom.dbapp.network.PreSchoolsByGPSTask;
import com.example.eom.dbapp.vo.KidsCenterData;
import com.example.eom.dbapp.vo.PreSchoolData;

import java.util.ArrayList;

public class KidsCenterList extends AppCompatActivity {
    KidsCenterAdpater adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kidscenter_list);

        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.rv_kidscenter);
        RecyclerView.ItemDecoration itemDecoration =
                new DividerItemDecoration(this, LinearLayoutManager.VERTICAL);
        recyclerView.addItemDecoration(itemDecoration);

        final ArrayList<KidsCenterData> arrayList = new ArrayList<>();
//        for(int i=0 ;i<30;i++){
//            arrayList.add(new KidsCenterData("temp","0108978512","경기도용인시시흥구기흥구","경기도","영이;ㄴ시",1234,122,45456.5,4565));
//        }
         adapter = new KidsCenterAdpater(this,arrayList);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        SharedPreferences sp = getSharedPreferences(MySharedPreferences.NAME,0);
        Double latitude = Double.parseDouble(sp.getString(MySharedPreferences.USER_LATITUDE,"35"));
        Double longitude = Double.parseDouble(sp.getString(MySharedPreferences.USER_LONGITUDE,"123"));

        new KidsCenterByGPSTask(latitude,longitude){
            @Override
            protected void onPostExecute(ArrayList<KidsCenterData> kidsCenterDatas) {
                super.onPostExecute(kidsCenterDatas);
                arrayList.clear();
                arrayList.addAll(kidsCenterDatas);
                adapter.notifyDataSetChanged();
            }
        }.execute("");

    }
}

