package com.example.eom.dbapp.network;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import static com.example.eom.dbapp.network.NetworkManager.setupConnection;

/**
 * Created by dhtpr on 2017-06-08.
 */

public abstract class JustStringTask extends AsyncTask<String, Void,String> {
    String murl;
    int id;

    protected JustStringTask(String url) {
        this.murl=url;
    }

    @Override
    protected String doInBackground(String... params) {
        InputStream inputStream;
        String strResult = "";
        String result = null;
        try {
            URL url = new URL(NetworkManager.serverIP + murl);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id",id);
            String strJson = "data=" + jsonObject.toString();
            Log.d("parsestring", strJson);
            setupConnection(strJson, urlConnection);
            inputStream = urlConnection.getInputStream();

            if (inputStream != null)
                strResult = NetworkManager.convertInputStreamToString(inputStream);
            else
                strResult = "Did not work!";
            Log.d("parsestring", strResult);
            return strResult;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}