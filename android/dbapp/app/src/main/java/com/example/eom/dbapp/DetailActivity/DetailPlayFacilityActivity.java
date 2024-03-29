package com.example.eom.dbapp.DetailActivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.eom.dbapp.R;
import com.example.eom.dbapp.network.DetailByIDTask;

import org.json.JSONObject;

public class DetailPlayFacilityActivity extends AppCompatActivity {
    TextView name, contents;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_play_facility);
        name=(TextView)findViewById(R.id.tv_detail_name);
        contents=(TextView)findViewById(R.id.tv_detail_contents);
        new DetailByIDTask(getIntent().getIntExtra("id",1),"/playFacility/detail/","playfacility"){
            @Override
            protected void onPostExecute(JSONObject jsonObject) {
                super.onPostExecute(jsonObject);try{
                    name.setText(jsonObject.getString("name"));
                    contents.setText(
                            "이름 \n"  +jsonObject.getString("name")+ "\n"+"\n"+
                                "주소 " +"\n"+     jsonObject.getString("si_do")+
                                    jsonObject.getString("si_gun_gu")+ "\n"+"\n"+
                                    "세부주소 : " +"\n" + jsonObject.getString("address")+"\n"+"\n"+
                                   "설치장소 \n" + jsonObject.getString("install_place")+"\n"+"\n"+
                            "우수 놀이공원" + jsonObject.getString("is_excellent")+"\n"+"\n" +
                                    "실내/외 놀이터 \n" +jsonObject.getString("indoor_outdoor")+"\n"+"\n" +
                                    "사유 놀이터 여부\n " + jsonObject.getString("public_private") +
                                    "\n"+"\n" +
                                    "설치 일자" + jsonObject.getString("install_date") + "\n" +"\n" +
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