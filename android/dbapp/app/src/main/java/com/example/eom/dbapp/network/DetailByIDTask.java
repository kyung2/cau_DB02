package com.example.eom.dbapp.network;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;

import com.example.eom.dbapp.MySharedPreferences;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import static com.example.eom.dbapp.network.NetworkManager.setupConnection;

/**
 * Created by dhtpr on 2017-06-08.
 */

public abstract class DetailByIDTask extends AsyncTask<String, Void,JSONObject> {
    String murl,parsestring;
    int id;

    protected DetailByIDTask(int id, String url, String parsestring) {
        this.murl=url;
        this.parsestring=parsestring;
        this.id=id;
    }

    @Override
    protected JSONObject doInBackground(String... params) {
        InputStream inputStream;
        String strResult = "";
        String result = null;
        JSONObject item = new JSONObject();
        try {
            URL url = new URL(NetworkManager.serverIP + murl);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", id);
            String strJson = "data=" + jsonObject.toString();
            Log.d("parsestring", strJson);
            setupConnection(strJson, urlConnection);
            inputStream = urlConnection.getInputStream();

            if (inputStream != null)
                strResult = NetworkManager.convertInputStreamToString(inputStream);
            else
                strResult = "Did not work!";
            Log.d("parsestring", strResult);
            item = new JSONArray(new JSONObject(strResult).getString(parsestring)).getJSONObject(0).getJSONObject("fields");
            return item;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return item;
    }
}