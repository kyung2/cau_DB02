package com.example.eom.dbapp.DetailActivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.eom.dbapp.R;
//import com.example.eom.dbapp.DetailActivity.R;
import com.example.eom.dbapp.network.DetailByIDTask;
import com.example.eom.dbapp.network.JustStringTask;

import org.json.JSONObject;

import java.net.URLDecoder;
import java.net.URLEncoder;

public class JustStringActivity extends AppCompatActivity {
    TextView name, contents;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_just_string);

        contents=(TextView)findViewById(R.id.tv_just_string_content);
        new JustStringTask(getIntent().getStringExtra("url")){//"/hospital/gps/"
            @Override
            protected void onPostExecute(String  string) {
                super.onPostExecute(string);try{
//                    name.setText(jsonObject.getString("name"));
                    contents.setText(URLDecoder.decode(string, "UTF-8"));

                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        }.execute("");
    }
}