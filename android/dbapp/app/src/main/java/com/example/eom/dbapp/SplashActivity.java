package com.example.eom.dbapp;
/**
 *
 */

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashActivity extends AppCompatActivity {

    private final int SPLASH_DISPLAY_LENGTH = 5000; // 이 millisecond가 지난 후 다음 페이지가 나온다.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                Intent mainIntent;
                //이 if 문이 로그인이 되어 있는지 아닌지를 본다.
                if(SplashActivity.this.getSharedPreferences(MySharedPreferences.NAME,0).getBoolean(MySharedPreferences.IS_LOGIN,false)){
                    mainIntent= new Intent(SplashActivity.this,MainActivity.class);
                }else{
                    mainIntent= new Intent(SplashActivity.this,LoginActivity.class);
                }
                SplashActivity.this.startActivity(mainIntent);
                SplashActivity.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}
