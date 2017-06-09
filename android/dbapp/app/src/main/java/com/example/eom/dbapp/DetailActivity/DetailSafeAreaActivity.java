package com.example.eom.dbapp.DetailActivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.eom.dbapp.R;
import com.example.eom.dbapp.network.DetailByIDTask;

import org.json.JSONObject;


/**
 * Created by hyunkyung on 2017-06-09.
 */

public class DetailSafeAreaActivity extends AppCompatActivity {
    TextView name, contents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_safe_area);
        name=(TextView)findViewById(R.id.tv_detail_name);
        contents=(TextView)findViewById(R.id.tv_detail_contents);
        new DetailByIDTask(getIntent().getIntExtra("id",1),"/safeArea/detail/","safearea"){
            @Override
            protected void onPostExecute(JSONObject jsonObject) {
                super.onPostExecute(jsonObject);try{
                    name.setText(jsonObject.getString("facility_name"));
                    contents.setText(
                            "이름 "+"\n"+ jsonObject.getString("facility_name")+"\n"+"\n"+
                                    "주소 "   +"\n"+jsonObject.getString("si_do")+jsonObject.getString("si_gun_gu")+"\n"+"\n"+
//                                    "전화번호  "+jsonObject.getString("tel")+"\n"+"\n"+
                                    "경찰서  "+jsonObject.getString("police_office")+"\n"+"\n"+
                                    "CCTV 수   "+jsonObject.getInt("NumofCCTV")+"\n"+"\n"+

                                    "위도  " +jsonObject.getDouble("latitude")+"\n"+
                                    "경도  "+jsonObject.getDouble("longitude")
                    );
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        }.execute("");
    }
}