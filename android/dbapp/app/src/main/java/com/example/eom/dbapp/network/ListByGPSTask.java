package com.example.eom.dbapp.network;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;

import com.example.eom.dbapp.MySharedPreferences;
import com.example.eom.dbapp.vo.KidsCenterData;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import static com.example.eom.dbapp.network.NetworkManager.setupConnection;

/**
 * Created by dhtpr on 2017-06-08.
 */

public abstract class ListByGPSTask extends AsyncTask<String, Void,JSONArray> {
    //data={"user_id":19,"longitude":126.95,"latitude":37.50,"range":1}37.50,126.95
    private double latitude,longitude;
    String murl,parsestring;

    protected ListByGPSTask(Context context, String url,String parsestring) {
        SharedPreferences sp = context.getSharedPreferences(MySharedPreferences.NAME,0);
        latitude = Double.parseDouble(sp.getString(MySharedPreferences.USER_LATITUDE,"35"));
        longitude = Double.parseDouble(sp.getString(MySharedPreferences.USER_LONGITUDE,"123"));
        this.murl=url;
        this.parsestring=parsestring;
    }

    @Override
    protected JSONArray doInBackground(String... params) {
        InputStream inputStream;
        String strResult = "";
        String result = null;
        JSONArray items = new JSONArray();
        try {
            URL url = new URL(NetworkManager.serverIP + murl);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("latitude", latitude);
            jsonObject.put("longitude", longitude);
            String strJson = "data=" + jsonObject.toString();
            Log.d("parsestring", strJson);
            setupConnection(strJson, urlConnection);
            inputStream = urlConnection.getInputStream();

            if (inputStream != null)
                strResult = NetworkManager.convertInputStreamToString(inputStream);
            else
                strResult = "Did not work!";
            Log.d("parsestring", strResult);
            items = new JSONObject(strResult).getJSONArray(parsestring);
            return items;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return items;
    }
}