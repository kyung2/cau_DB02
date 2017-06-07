package com.example.eom.dbapp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.eom.dbapp.Adapter.FunctionListAdapter;
import com.example.eom.dbapp.Adapter.PreSchoolAdpater;
import com.example.eom.dbapp.network.PreSchoolsByGPSTask;
import com.example.eom.dbapp.vo.PreSchoolData;

import java.util.ArrayList;

public class PreSchoolListActivity extends AppCompatActivity {
    PreSchoolAdpater adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preshcool_list);
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.rv_preschools);
        final ArrayList<PreSchoolData> arrayList = new ArrayList<>();
        SharedPreferences sp = getSharedPreferences(MySharedPreferences.NAME,0);
        Double latitude = Double.parseDouble(sp.getString(MySharedPreferences.USER_LATITUDE,"35"));
        Double longitude = Double.parseDouble(sp.getString(MySharedPreferences.USER_LONGITUDE,"123"));

        new PreSchoolsByGPSTask(latitude,longitude){
            @Override
            protected void onPostExecute(ArrayList<PreSchoolData> preSchoolDatas) {
                super.onPostExecute(preSchoolDatas);
                arrayList.clear();
                arrayList.addAll(preSchoolDatas);
                adapter.notifyDataSetChanged();
            }
        }.execute("");
        /*
        * (String name, String address,String si_do,String si_gun_gu,String tel ,String fax, String assess_certification_type,
                         int id, int postcode ,int playground_num , int nursing_room_area , int nursing_room_num, int capacity ,int NumofCCTV,
                         Date pause_end_date , Date pause_start_date, Date close_date, Date permit_date,
                         double latitude, double longitude,
                         boolean operation_state , boolean school_bus)
                         */
//        for(int i=0 ;i<30;i++){
//
//            arrayList.add(new PreSchoolData("유치원"+i,"서울시 양천구 신정"+i+"동","0102252225"));
//        }
         adapter = new PreSchoolAdpater(this,arrayList);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }
}
