package com.example.eom.dbapp.DetailActivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.eom.dbapp.R;
import com.example.eom.dbapp.network.DetailByIDTask;

import org.json.JSONObject;

public class DetailTrafficaccidentareaActivity extends AppCompatActivity {
    TextView name, contents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_trafficaccidentarea);
        name=(TextView)findViewById(R.id.tv_detail_name);
        contents=(TextView)findViewById(R.id.tv_detail_contents);
        new DetailByIDTask(getIntent().getIntExtra("id",1),"/trafficAccidentArea/detail/","trafficaccidentarea"){
            @Override
            protected void onPostExecute(JSONObject jsonObject) {
                super.onPostExecute(jsonObject);try{
                    name.setText(jsonObject.getString("name"));
                    contents.setText(

                            "주소 "+"\n"+ jsonObject.getString("si_do ")+ jsonObject.getString("si_gun_gu")+"\n"+"\n"+
                                    "담당 경찰서 "   +"\n"+jsonObject.getString("police_office") +"\n"+"\n"+

                                    "근처 학교 "   +"\n"+jsonObject.getString("near_school") +"\n"+"\n"+
                                    "사건 발생 횟수 " + jsonObject.getInt("occur_cnt") + "\n" +
                                    "사망자 수" + jsonObject.getInt("death_cnt") + "\n" +
                                    "serious 사고 " + jsonObject.getInt("serious_cnt") + "\n" +
                                    "약한 사고 " + jsonObject.getInt("light_cnt") + "\n" +
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