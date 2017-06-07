package com.example.eom.dbapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.eom.dbapp.network.PreSchoolsDetailTask;
import com.example.eom.dbapp.vo.KidsCafeData;
import com.example.eom.dbapp.vo.PreSchoolData;

import org.w3c.dom.Text;

/**
 * Created by hyunkyung on 2017-06-05.
 */
public class DetailPreSchoolActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_pre_school);
        new PreSchoolsDetailTask(getIntent().getIntExtra("id",1)){
            @Override
            protected void onPostExecute(PreSchoolData preSchoolData) {
                super.onPostExecute(preSchoolData);
                setData(preSchoolData);
            }
        }.execute("");
//        PreSchoolData preSchoolData = PreSchoolData.getSamplePreschoolData();
//        setData(preSchoolData);

    }

    private void setData(PreSchoolData preSchoolData) {
        TextView tv_big_name = (TextView) findViewById(R.id.tv_detail_pre_school_big_name);
        tv_big_name.setText(preSchoolData.name);

        TextView tv_name = (TextView) findViewById(R.id.tv_detail_pre_school_name);
        tv_name.setText(preSchoolData.name);

        TextView tv_permit_date = (TextView) findViewById(R.id.tv_detail_pre_school_permit_date);
        tv_permit_date.setText(MyUtil.getStringFromDate(preSchoolData.permit_date));

        TextView tv_type = (TextView) findViewById(R.id.tv_detail_pre_school_type);
        tv_type.setText("" + preSchoolData.assess_certification_type);

        TextView tv_address = (TextView) findViewById(R.id.tv_detail_pre_school_address);
        tv_address.setText(preSchoolData.address);

        TextView tv_postcode = (TextView) findViewById(R.id.tv_detail_pre_school_postcode);
        tv_postcode.setText(""+preSchoolData.postcode);

        TextView tv_si_do = (TextView) findViewById(R.id.tv_detail_pre_school_si_do);
        tv_si_do.setText(preSchoolData.si_do);

        TextView tv_si_gun_gu = (TextView) findViewById(R.id.tv_detail_pre_school_si_gun_gu);
        tv_si_gun_gu.setText(preSchoolData.si_gun_gu);

        TextView tv_operation_state = (TextView) findViewById(R.id.tv_detail_pre_school_close_date);
        tv_operation_state.setText(MyUtil.getStringFromDate(preSchoolData.close_date));

        TextView tv_pause_start_date = (TextView) findViewById(R.id.tv_detail_pre_school_pause_start_date);
        tv_pause_start_date.setText(MyUtil.getStringFromDate(preSchoolData.pause_start_date));

        TextView tv_pause_end_date = (TextView) findViewById(R.id.tv_detail_pre_school_pause_end_date);
        tv_pause_end_date.setText(MyUtil.getStringFromDate(preSchoolData.pause_end_date));

        TextView tv_latitude = (TextView) findViewById(R.id.tv_detail_pre_school_latitude);
        tv_latitude.setText("" + preSchoolData.latitude);

        TextView tv_longitude = (TextView) findViewById(R.id.tv_detail_pre_school_longitude);
        tv_longitude.setText("" + preSchoolData.longitude);

        TextView tv_tel = (TextView) findViewById(R.id.tv_detail_pre_school_tel);
        tv_tel.setText(preSchoolData.tel);

        TextView tv_fax = (TextView) findViewById(R.id.tv_detail_pre_school_fax);
        tv_fax.setText(preSchoolData.fax);

        TextView tv_capacity = (TextView) findViewById(R.id.tv_detail_pre_school_capacity);
        tv_capacity.setText(""+preSchoolData.capacity);

        TextView tv_school_bus = (TextView) findViewById(R.id.tv_detail_pre_school_shcool_bus);
        tv_school_bus.setText(""+preSchoolData.school_bus);

        TextView tv_playground_num = (TextView) findViewById(R.id.tv_detail_pre_school_playground_num);
        tv_playground_num.setText(""+preSchoolData.playground_num);

        TextView tv_nursing_room_area = (TextView) findViewById(R.id.tv_detail_pre_school_nursing_room_area);
        tv_nursing_room_area.setText(""+preSchoolData.nursing_room_area);

        TextView tv_nursing_room_num = (TextView) findViewById(R.id.tv_detail_pre_school_nursing_room_num);
        tv_nursing_room_num.setText(""+preSchoolData.nursing_room_num);

        TextView tv_NumofCCTV = (TextView) findViewById(R.id.tv_detail_pre_school_NumofCCTV);
        tv_NumofCCTV.setText(""+preSchoolData.NumofCCTV);
    }
}

