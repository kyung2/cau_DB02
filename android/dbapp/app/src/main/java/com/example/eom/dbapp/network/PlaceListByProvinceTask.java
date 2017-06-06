package com.example.eom.dbapp.network;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import static com.example.eom.dbapp.network.NetworkManager.setupConnection;

public abstract class PlaceListByProvinceTask extends AsyncTask<String, Void, String> {
    //data={"username":"하이예나","email":"aa@aa.aa","password":"password","birth":"1994-09-16"}
    private String mProvince1str, mProvince2str;
    private int mUserID;

    protected PlaceListByProvinceTask(String province1str, String province2str, int userID) {
        mUserID = userID;
        mProvince1str = province1str;
        mProvince2str = province2str;
    }

    @Override
    protected String doInBackground(String... params) {
        InputStream inputStream;
        String strResult = "";
        String result = null;
        try {
            URL url = new URL(NetworkManager.serverIP + "/getMyPlacesbyProvince2/");
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("user_id", mUserID);
            jsonObject.put("province1str", URLEncoder.encode(mProvince1str, "UTF-8"));
            jsonObject.put("province2str", URLEncoder.encode(mProvince2str, "UTF-8"));
            String strJson = "data=" + jsonObject.toString();
            Log.d("getPlace", strJson);
            setupConnection(strJson, urlConnection);
            inputStream = urlConnection.getInputStream();

            if (inputStream != null)
                strResult = NetworkManager.convertInputStreamToString(inputStream);
            else
                strResult = "Did not work!";
            Log.d("getPlace", strResult);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return strResult;
    }
}