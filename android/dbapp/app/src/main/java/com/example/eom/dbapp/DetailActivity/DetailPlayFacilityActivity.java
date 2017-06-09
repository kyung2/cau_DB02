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
        new DetailByIDTask(getIntent().getIntExtra("id",1),"/playFacility/detail/","playfacility"){
            @Override
            protected void onPostExecute(JSONObject jsonObject) {
                super.onPostExecute(jsonObject);try{
                    name.setText(jsonObject.getString("name"));
                    contents.setText(
                            jsonObject.getString("name")+"</br>"+"\n"+"\n"+
                                    jsonObject.getString("si_do")+"\n" +
                                    jsonObject.getString("si_gun_gu")+ "\n"+"\n"+
                                    jsonObject.getString("install_place")+"\n"
                    );
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        }.execute("");
    }
}