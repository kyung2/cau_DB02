package com.example.eom.dbapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.eom.dbapp.ListActivity.ChildCareCenterListActivity;
import com.example.eom.dbapp.ListActivity.ElmSchoolListActivity;
import com.example.eom.dbapp.ListActivity.HospitalListActivity;
import com.example.eom.dbapp.ListActivity.KidsCafeListActivity;
import com.example.eom.dbapp.ListActivity.KidsCenterList;
import com.example.eom.dbapp.ListActivity.PlayFacilityListActivity;
import com.example.eom.dbapp.ListActivity.SafeAreaListActivity;
import com.example.eom.dbapp.ListActivity.TrafficAccidentAreaListActivity;
import com.example.eom.dbapp.ListActivity.WalfareServiceListActivity;

public class OthersActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_others);
        findViewById(R.id.tv_other_kidsCenter).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(OthersActivity.this, KidsCenterList.class));
            }
        });
        findViewById(R.id.tv_other_childCareCenter).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(OthersActivity.this, ChildCareCenterListActivity.class));
            }
        });
        findViewById(R.id.tv_other_elemSchool).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(OthersActivity.this, ElmSchoolListActivity.class));
            }
        });
        findViewById(R.id.tv_other_hospital).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(OthersActivity.this, HospitalListActivity.class));
            }
        });
        findViewById(R.id.tv_other_playFacility).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(OthersActivity.this, PlayFacilityListActivity.class));
            }
        });
        findViewById(R.id.tv_other_safeArea).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(OthersActivity.this, SafeAreaListActivity.class));
            }
        });
        findViewById(R.id.tv_other_trafficAccidentArea).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(OthersActivity.this, TrafficAccidentAreaListActivity.class));
            }
        });
        findViewById(R.id.tv_other_walfareService).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(OthersActivity.this, WalfareServiceListActivity.class));
            }
        });
    }
}
