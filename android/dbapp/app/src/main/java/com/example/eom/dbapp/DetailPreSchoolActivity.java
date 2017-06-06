package com.example.eom.dbapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.eom.dbapp.vo.KidsCafeData;
import com.example.eom.dbapp.vo.PreSchoolData;

/**
 * Created by hyunkyung on 2017-06-05.
 */
public class DetailPreSchoolActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_pre_school);
    }

    private void setData(PreSchoolData preSchoolData) {
        TextView tv_name = (TextView) findViewById(R.id.tv_detail_pre_school_name);
        tv_name.setText(preSchoolData.name);
        TextView tv_permit_date = (TextView) findViewById(R.id.tv_detail_pre_school_permit_date);
        tv_permit_date.setText((CharSequence) preSchoolData.permit_date);
        TextView tv_address = (TextView) findViewById(R.id.tv_detail_pre_school_address);
        tv_address.setText(preSchoolData.address);
        TextView tv_postcode = (TextView) findViewById(R.id.tv_detail_pre_school_postcode);
        tv_postcode.setText(preSchoolData.postcode);
        TextView tv_si_do = (TextView) findViewById(R.id.tv_detail_pre_school_si_do);
        tv_si_do.setText(preSchoolData.si_do);
        TextView tv_si_gun_gu = (TextView) findViewById(R.id.tv_detail_pre_school_si_gun_gu);
        tv_si_gun_gu.setText(preSchoolData.si_gun_gu);
        TextView tv_operation_state = (TextView) findViewById(R.id.tv_detail_pre_school_close_date);
        tv_operation_state.setText("" + preSchoolData.close_date);
        TextView tv_latitude = (TextView) findViewById(R.id.tv_detail_pre_school_latitude);
        tv_latitude.setText("" + preSchoolData.latitude);
        TextView tv_longitude = (TextView) findViewById(R.id.tv_detail_pre_school_longitude);
        tv_longitude.setText("" + preSchoolData.longitude);

    }
}


