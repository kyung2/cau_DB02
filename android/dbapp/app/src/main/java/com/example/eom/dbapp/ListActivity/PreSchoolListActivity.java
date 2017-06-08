package com.example.eom.dbapp.ListActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.eom.dbapp.Adapter.PreSchoolAdpater;
import com.example.eom.dbapp.MySharedPreferences;
import com.example.eom.dbapp.R;
import com.example.eom.dbapp.network.PreSchoolsByGPSTask;
import com.example.eom.dbapp.network.PreSchoolsBysidoTask;
import com.example.eom.dbapp.network.PreSchoolsBysignguTask;
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
        findViewById(R.id.bt_pre_list_search).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String si_do = ((EditText)findViewById(R.id.et_detail_preschool_si_input)).getText().toString();
                String si_gun = ((EditText)findViewById(R.id.et_detail_preschool_sigungu_input)).getText().toString();
                if(si_do.equals("")){
                    Toast.makeText(PreSchoolListActivity.this,"시 도를 입력해주세요",Toast.LENGTH_SHORT).show();
                }else if (si_gun.equals("")) {
                    Toast.makeText(PreSchoolListActivity.this, "시 군 구를 입력해주세요", Toast.LENGTH_SHORT).show();
                    new PreSchoolsBysidoTask(si_do){
                        @Override
                        protected void onPostExecute(ArrayList<PreSchoolData> preSchoolDatas) {
                            super.onPostExecute(preSchoolDatas);
                            arrayList.clear();
                            arrayList.addAll(preSchoolDatas);
                            adapter.notifyDataSetChanged();
                        }
                    }.execute("");
                }else{
                    Toast.makeText(PreSchoolListActivity.this,"잘했어요",Toast.LENGTH_SHORT).show();
                    new PreSchoolsBysignguTask(si_do,si_gun){
                        @Override
                        protected void onPostExecute(ArrayList<PreSchoolData> preSchoolDatas) {
                            super.onPostExecute(preSchoolDatas);
                            arrayList.clear();
                            arrayList.addAll(preSchoolDatas);
                            adapter.notifyDataSetChanged();
                        }
                    }.execute("");
                }
            }
        });
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
