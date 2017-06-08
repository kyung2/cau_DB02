package com.example.eom.dbapp.DetailActivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.eom.dbapp.R;
import com.example.eom.dbapp.network.DetailByIDTask;
import com.example.eom.dbapp.network.PreSchoolsDetailTask;
import com.example.eom.dbapp.vo.ElmSchoolData;
import com.example.eom.dbapp.vo.KidsCenterData;

import org.json.JSONObject;

public class DetailElmSchoolActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_elm_school);
        new DetailByIDTask(getIntent().getIntExtra("id",1),"/elemSchool/detail/","elemschool"){
            @Override
            protected void onPostExecute(JSONObject jsonObject) {
                super.onPostExecute(jsonObject);try{
                    ElmSchoolData item = new ElmSchoolData(
                            getIntent().getIntExtra("id",1),


                            jsonObject.getString("name"),
                            jsonObject.getString("tel"),
                            jsonObject.getInt("postcode"),


                            jsonObject.getString("si_do"),
                            jsonObject.getString("si_gun_gu"),

                            jsonObject.getString("address"),
                            jsonObject.getString("homepage"),


                            jsonObject.getString("public_private"),
                            jsonObject.getDouble("longitude"),
                            jsonObject.getDouble("latitude"));


                    setData(item);
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        }.execute("");
    }

    private void setData(ElmSchoolData elmSchoolData) {
        TextView tv_big_name = (TextView) findViewById(R.id.tv_detail_elm_school_big_name);
        tv_big_name.setText(elmSchoolData.name);

        TextView tv_name = (TextView) findViewById(R.id.tv_detail_elm_school_name);
        tv_name.setText(elmSchoolData.name);

        TextView tv_tel = (TextView) findViewById(R.id.tv_detail_elm_school_tel);
        tv_tel.setText(elmSchoolData.tel);


        TextView tv_postcode = (TextView) findViewById(R.id.tv_detail_elm_school_postcode);
        tv_postcode.setText(""+elmSchoolData.postcode);

        TextView tv_si_do = (TextView) findViewById(R.id.tv_detail_elm_school_si_do);
        tv_si_do.setText(elmSchoolData.si_do);

        TextView tv_si_gun_gu = (TextView) findViewById(R.id.tv_detail_elm_school_si_gun_gu);
        tv_si_gun_gu.setText(elmSchoolData.si_gun_gu);

        TextView tv_address = (TextView) findViewById(R.id.tv_detail_elm_school_address);
        tv_address.setText(elmSchoolData.address);

        TextView tv_homepage = (TextView) findViewById(R.id.tv_detail_elm_school_homepage);
        tv_homepage.setText(elmSchoolData.homepage);

        TextView tv_public_private = (TextView) findViewById(R.id.tv_detail_elm_school_public_private);
        tv_public_private.setText("" + elmSchoolData.public_private);

        TextView tv_latitude = (TextView) findViewById(R.id.tv_detail_elm_school_latitude);
        tv_latitude.setText("" + elmSchoolData.latitude);

        TextView tv_longitude = (TextView) findViewById(R.id.tv_detail_elm_school_longitude);
        tv_longitude.setText("" + elmSchoolData.longitude);


    }
}

