package com.example.eom.dbapp.DetailActivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.eom.dbapp.R;
import com.example.eom.dbapp.network.DetailByIDTask;

import org.json.JSONObject;

public class DetailHospitalActivity extends AppCompatActivity {
    TextView name, contents;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_hospital);

        name=(TextView)findViewById(R.id.tv_detail_name);
        contents=(TextView)findViewById(R.id.tv_detail_contents);
        new DetailByIDTask(getIntent().getIntExtra("id",1),"/hospital/detail/","hospital"){
            @Override
            protected void onPostExecute(JSONObject jsonObject) {
                super.onPostExecute(jsonObject);try{
                    name.setText(jsonObject.getString("name"));
                    contents.setText(
                            jsonObject.getString("name")+"</br>"+"\n"+"\n"+
                                    jsonObject.getString("si_do")+"\n"+
                                    jsonObject.getString("si_gun_gu")+"\n"+
                                    jsonObject.getString("tel")+"\n"+"\n"
                                     );
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        }.execute("");
    }
}