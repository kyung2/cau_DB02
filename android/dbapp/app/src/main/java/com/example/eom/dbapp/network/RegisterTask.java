package com.example.eom.dbapp.network;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import static com.example.eom.dbapp.network.NetworkManager.setupConnection;

public abstract class RegisterTask extends AsyncTask<String, Void, String> {
    //data={"username":"하이예나","email":"aa@aa.aa","password":"password","birth":"1994-09-16"}
    private String mUsername, mPassword, mBirth, mUserID,mSex,msi,mgun;
    int mChildAge;

    protected RegisterTask(String nick_name, String pw, String birth, String user_id,String childSex,int childage,String si,String gun) {
        mUsername = nick_name; //닉네임
        mBirth = birth; //아이나이
        mPassword = pw; //패스워드
        mUserID = user_id;//유저 가 이메일!
        mSex = childSex;
        mChildAge=childage;
        msi=si;
        mgun=gun;
    }

    @Override
    protected String doInBackground(String... params) {
        InputStream inputStream;
        String strResult = "";
        String result = null;
        try {

            //user_id=data['user_id'], user_pw=data['pw'], nick_name=data['nick_name'],
//            child_sex = data['child_sex'], child_age = data['child_age'],
//                    preSchool_id_id=data['preschool_id'], si_do=data['si_do'], si_gun_gu=data['si_gun_gu']
            URL url = new URL(NetworkManager.serverIP + "/signup/");
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("user_id", mUserID);
            jsonObject.put("pw", mPassword);
            jsonObject.put("nick_name", URLEncoder.encode(mUsername, "UTF-8"));
            jsonObject.put("child_sex", mSex);
            jsonObject.put("child_age", mChildAge);
            jsonObject.put("preschool_id", 1);
            jsonObject.put("si_do", URLEncoder.encode(msi, "UTF-8"));
            jsonObject.put("si_gun_gu",URLEncoder.encode(mgun, "UTF-8") );

            String strJson = "data=" + jsonObject.toString();
            Log.d("getPlace", strJson);
            setupConnection(strJson, urlConnection);
            inputStream = urlConnection.getInputStream();

            if (inputStream != null)
                strResult = NetworkManager.convertInputStreamToString(inputStream);
            else
                strResult = "Did not work!";
            Log.d("RegisterTask", strResult);

        } catch (Exception e) {
            e.printStackTrace();
        }


        return strResult;
    }
}