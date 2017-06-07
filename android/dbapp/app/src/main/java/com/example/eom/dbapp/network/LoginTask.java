package com.example.eom.dbapp.network;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import static com.example.eom.dbapp.network.NetworkManager.setupConnection;

public abstract class LoginTask extends AsyncTask<String, Void, String> {
    //data={"username":"하이예나","email":"aa@aa.aa","password":"password","birth":"1994-09-16"}
    private String mPassword, mUserID;
    private String TAG = "LoginTask";
    protected LoginTask( String userID,String password) {
        mPassword = password;
        mUserID = userID;
    }

    @Override
    protected String doInBackground(String... params) {
        InputStream inputStream;
        String strResult = "";
        String result = null;
        try {
            URL url = new URL(NetworkManager.serverIP + "/login/");
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("user_id", mUserID);
            jsonObject.put("pw", mPassword);
            String strJson = "data=" + jsonObject.toString();
            Log.d(TAG, strJson);
            setupConnection(strJson, urlConnection);
            inputStream = urlConnection.getInputStream();

            if (inputStream != null)
                strResult = NetworkManager.convertInputStreamToString(inputStream);
            else
                strResult = "Did not work!";
            Log.d(TAG, strResult);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return strResult;
    }
}