package com.example.eom.dbapp;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.eom.dbapp.vo.KidsCafeData;

/**
 * Created by hyunkyung on 2017-06-05.
 */
public class DetailKidsCafeActivity extends AppCompatActivity {
Cursor cursor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_kids_cafe);
    }
    private void setData(KidsCafeData kidsCafeData){
        TextView textView = (TextView)findViewById(R.id.tv_detail_kidcafe_address);
        textView.setText(kidsCafeData.address);
        //TODO 이런식으로 다 연결하기
    }
}
