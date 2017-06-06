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
    private String mUsername, mEmail, mPassword, mBirth, mUserID;

    protected RegisterTask(String username, String email, String password, String birth, String userID) {
        mUsername = username;
        mEmail = email;
        mBirth = birth;
        mPassword = password;
        mUserID = userID;
    }

    @Override
    protected String doInBackground(String... params) {
        InputStream inputStream;
        String strResult = "";
        String result = null;
        try {
            URL url = new URL(NetworkManager.serverIP + "/register/");
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("username", URLEncoder.encode(mUsername, "UTF-8"));
            jsonObject.put("user_id", mUserID);
            jsonObject.put("email", mEmail);
            jsonObject.put("password", mPassword);
            jsonObject.put("birth", mBirth);
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