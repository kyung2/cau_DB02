package com.example.eom.dbapp.DetailActivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.eom.dbapp.R;
import com.example.eom.dbapp.network.DetailByIDTask;

import org.json.JSONObject;

public class DetailChildCareCenterActivity extends AppCompatActivity {
    TextView name, contents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_child_care_center);
        name=(TextView)findViewById(R.id.tv_detail_name);
        contents=(TextView)findViewById(R.id.tv_detail_contents);
        new DetailByIDTask(getIntent().getIntExtra("id",1),"/childCareCenter/detail/","childcarecenter"){
            @Override
            protected void onPostExecute(JSONObject jsonObject) {
                super.onPostExecute(jsonObject);try{
                    name.setText(jsonObject.getString("name"));
                    contents.setText(
                            "이름 "+"\n"+ jsonObject.getString("name")+"\n"+"\n"+
                                 "주소 "   +"\n"+jsonObject.getString("address")+"\n"+"\n"+
                                    "전화번호  "+jsonObject.getString("tel")+"\n"+"\n"+
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
