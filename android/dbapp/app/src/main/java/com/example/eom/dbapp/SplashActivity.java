package com.example.eom.dbapp;
/**
 *
 */

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import static com.example.eom.dbapp.MySharedPreferences.USER_LONGITUDE;

public class SplashActivity extends AppCompatActivity {

    private final int SPLASH_DISPLAY_LENGTH = 5000; // 이 millisecond가 지난 후 다음 페이지가 나온다.
    LocationManager mLM;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        mLM = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED) {

            // 이 권한을 필요한 이유를 설명해야하는가?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {

                // 다이어로그같은것을 띄워서 사용자에게 해당 권한이 필요한 이유에 대해 설명합니다
                // 해당 설명이 끝난뒤 requestPermissions()함수를 호출하여 권한허가를 요청해야 합니다

            } else {

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_CONTACTS},
                        1);

                // 필요한 권한과 요청 코드를 넣어서 권한허가요청에 대한 결과를 받아야 합니다

            }
        }else{
            registerLocationUpdates();
        }


        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
            Intent mainIntent;
            //이 if 문이 로그인이 되어 있는지 아닌지를 본다.
            if(SplashActivity.this.getSharedPreferences(MySharedPreferences.NAME,0).getBoolean(MySharedPreferences.IS_LOGIN,true)){
                mainIntent= new Intent(SplashActivity.this,MainActivity.class);
            }else{
                mainIntent= new Intent(SplashActivity.this,LoginActivity.class);
            }
            SplashActivity.this.startActivity(mainIntent);
            SplashActivity.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }
    private void registerLocationUpdates() {
        try {
            mLM.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,
                    1000, 1, mLocationListener);
            mLM.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                    1000, 1, mLocationListener);
        }catch (SecurityException e){
            e.printStackTrace();
        }
    }


    private final LocationListener mLocationListener = new LocationListener() {
        public void onLocationChanged(Location location) {

            if (location.getProvider().equals(LocationManager.GPS_PROVIDER)) {
                double longitude = location.getLongitude();    //경도
                double latitude = location.getLatitude();         //위도
                float accuracy = location.getAccuracy();        //신뢰도
            }
            else {
                double longitude = location.getLongitude();    //경도
                double latitude = location.getLatitude();         //위도
                float accuracy = location.getAccuracy();        //신뢰도
            }
            SharedPreferences.Editor editor = SplashActivity.this.getSharedPreferences(MySharedPreferences.NAME,0).edit();
            editor.putString(MySharedPreferences.USER_LATITUDE,""+location.getLatitude());
            editor.putString(MySharedPreferences.USER_LONGITUDE,""+location.getLongitude());
            editor.apply();
            Log.d("SplashActivity","location : "+ location.getLatitude()+"  "+ location.getLongitude());
            mLM.removeUpdates(mLocationListener);
        }
        public void onProviderDisabled(String provider) {
        }

        public void onProviderEnabled(String provider) {
        }

        public void onStatusChanged(String provider, int status, Bundle extras) {
        }
    };

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1:

                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_COARSE_LOCATION)!= PackageManager.PERMISSION_GRANTED) {

                        // 이 권한을 필요한 이유를 설명해야하는가?
                        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_COARSE_LOCATION)) {

                            // 다이어로그같은것을 띄워서 사용자에게 해당 권한이 필요한 이유에 대해 설명합니다
                            // 해당 설명이 끝난뒤 requestPermissions()함수를 호출하여 권한허가를 요청해야 합니다

                        } else {

                            ActivityCompat.requestPermissions(this,
                                    new String[]{Manifest.permission.READ_CONTACTS},
                                    2);

                            // 필요한 권한과 요청 코드를 넣어서 권한허가요청에 대한 결과를 받아야 합니다

                        }
                    }
                } else {
                }
                break;
            case 2:
                registerLocationUpdates();
        }
        return;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLM.removeUpdates(mLocationListener);
    }
}
