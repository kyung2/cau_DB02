package com.example.eom.dbapp.DetailActivity;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.eom.dbapp.MyUtil;
import com.example.eom.dbapp.R;
import com.example.eom.dbapp.network.KidsCafeDetailTask;
import com.example.eom.dbapp.network.PreSchoolsDetailTask;
import com.example.eom.dbapp.vo.KidsCafeData;
import com.example.eom.dbapp.vo.PreSchoolData;

import java.util.Date;

/**
 * Created by hyunkyung on 2017-06-05.
 */
public class DetailKidsCafeActivity extends AppCompatActivity {
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_kids_cafe);
        new KidsCafeDetailTask(getIntent().getIntExtra("id",1)){
            @Override
            protected void onPostExecute(KidsCafeData kidsCafeData) {
                super.onPostExecute(kidsCafeData);
                setData(kidsCafeData);
            }
        }.execute("");
//        KidsCafeData kidsCafeData = Kid.execute("");
// CafeData.getSampleKidsCafeData();
//        setData(kidsCafeData);
    }

    private void setData(KidsCafeData kidsCafeData) {
        TextView tv_bigName = (TextView) findViewById(R.id.tv_detail_kidcafe_big_name);
        tv_bigName.setText(kidsCafeData.name);

        TextView tv_name = (TextView) findViewById(R.id.tv_detail_kidcafe_name_input);
        tv_name.setText(kidsCafeData.name);

        TextView tv_permit_date = (TextView) findViewById(R.id.tv_detail_kidcafe_permit_date_input);
        tv_permit_date.setText(MyUtil.getStringFromDate(kidsCafeData.permit_date));

        TextView tv_address = (TextView) findViewById(R.id.tv_detail_kidcafe_address_input);
        tv_address.setText(kidsCafeData.address);

        TextView tv_postcode = (TextView) findViewById(R.id.tv_detail_kidcafe_postcode_input);
        tv_postcode.setText(""+kidsCafeData.postcode);

        TextView tv_hygiene_name = (TextView) findViewById(R.id.tv_detail_kidcafe_hygiene_name_input);
        tv_hygiene_name.setText(kidsCafeData.hygiene_name);

        TextView tv_si_do = (TextView) findViewById(R.id.tv_detail_kidcafe_si_do_input);
        tv_si_do.setText(kidsCafeData.si_do);

        TextView tv_si_gun_gu = (TextView) findViewById(R.id.tv_detail_kidcafe_si_gun_gu_input);
        tv_si_gun_gu.setText(kidsCafeData.si_gun_gu);

        TextView tv_operation_state = (TextView) findViewById(R.id.tv_detail_kidcafe_operation_state_input);
        tv_operation_state.setText(kidsCafeData.operation_state);

        TextView tv_multi_use = (TextView) findViewById(R.id.tv_detail_kidcafe_mutiple_use_input);
        tv_multi_use.setText(kidsCafeData.multi_use);

        TextView tv_latitude = (TextView) findViewById(R.id.tv_detail_kidcafe_latitude_input);
        tv_latitude.setText(""+kidsCafeData.latitude);

        TextView tv_longitude = (TextView) findViewById(R.id.tv_detail_kidcafe_longitude_input);
        tv_longitude.setText(""+kidsCafeData.longitude);


        // String multi_use, double latitude , double longitude, Date permit_date) {


        //TODO 이런식으로 다 연결하기
    }
}
