package com.example.eom.dbapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.eom.dbapp.network.KidsCafeDetailTask;
import com.example.eom.dbapp.network.KidsCenterDetailTask;
import com.example.eom.dbapp.vo.KidsCafeData;
import com.example.eom.dbapp.vo.KidsCenterData;
import com.example.eom.dbapp.vo.PreSchoolData;

public class DetailKidsCenterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_kids_center);
        new KidsCenterDetailTask(getIntent().getIntExtra("id",1)){
            @Override
            protected void onPostExecute(KidsCenterData KidsCe) {
                super.onPostExecute(KidsCe);
                setData(KidsCe);
            }
        }.execute("");
//        KidsCenterData kidsCenterData = KidsCenterData.getSampleKidsCenterData();
//        setData(kidsCenterData);
    }
    private void setData(KidsCenterData kidsCenterData) {
        TextView tv_big_name = (TextView) findViewById(R.id.tv_detail_kids_center_big_name);
        tv_big_name.setText(kidsCenterData.name);

        TextView tv_name = (TextView) findViewById(R.id.tv_detail_kids_center_name);
        tv_name.setText(kidsCenterData.name);

        TextView tv_tel = (TextView) findViewById(R.id.tv_detail_kids_center_tel);
        tv_tel.setText(kidsCenterData.tel);

        TextView tv_address = (TextView) findViewById(R.id.tv_detail_kids_center_address);
        tv_address.setText(kidsCenterData.address);


        TextView tv_si_do = (TextView) findViewById(R.id.tv_detail_kids_center_si_do);
        tv_si_do.setText(kidsCenterData.si_do);

        TextView tv_si_gun_gu = (TextView) findViewById(R.id.tv_detail_kids_center_si_gun_gu);
        tv_si_gun_gu.setText(kidsCenterData.si_gun_gu);

        TextView tv_postcode = (TextView) findViewById(R.id.tv_detail_kids_center_postcode);
        tv_postcode.setText(""+kidsCenterData.postcode);

        TextView tv_latitude = (TextView) findViewById(R.id.tv_detail_kids_center_latitude);
        tv_latitude.setText("" + kidsCenterData.latitude);

        TextView tv_longitude = (TextView) findViewById(R.id.tv_detail_kids_center_longitude);
        tv_longitude.setText("" + kidsCenterData.longitude);

        TextView tv_capacity = (TextView) findViewById(R.id.tv_detail_kids_center_capacity);
        tv_capacity.setText(""+kidsCenterData.capacity);

    }
}
