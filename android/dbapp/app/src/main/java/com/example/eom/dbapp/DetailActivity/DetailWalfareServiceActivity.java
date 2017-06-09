package com.example.eom.dbapp.DetailActivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.eom.dbapp.R;
import com.example.eom.dbapp.network.DetailByIDTask;

import org.json.JSONObject;

public class DetailWalfareServiceActivity  extends AppCompatActivity {
    TextView name, contents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_walfare_service);
        name=(TextView)findViewById(R.id.tv_detail_name);
        contents=(TextView)findViewById(R.id.tv_detail_contents);
        new DetailByIDTask(getIntent().getIntExtra("id",1),"/walfareService/detail","walfareservice"){
            @Override
            protected void onPostExecute(JSONObject jsonObject) {
                super.onPostExecute(jsonObject);try{
                    name.setText(jsonObject.getString("name"));
                    contents.setText(
                            "이름 "+"\n"+ jsonObject.getString("name")+"\n"+"\n"+
                                    "목적  " + "\n" + jsonObject.getString("purpose")+"\n"+"\n"+
                                    "센터 수 " + "\n" + jsonObject.getInt("center_int")+"\n"+"\n"+
                                    "센터이름 "   +"\n"+jsonObject.getString("center_name") + "\n"+ "\n" +
                                    "운영 " + "\n" + jsonObject.getString("operator") +"\n"+"\n"+
                                    "운영_org " + "\n" + jsonObject.getString("operation_org") +"\n"+"\n"+
                                    "운영 기간 " + "\n" + jsonObject.getString("operator")


                    );
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        }.execute("");
    }
}