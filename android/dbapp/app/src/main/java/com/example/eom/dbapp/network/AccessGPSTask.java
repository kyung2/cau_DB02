package com.example.eom.dbapp.network;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import static com.example.eom.dbapp.network.NetworkManager.setupConnection;

/**
 * Created by yena on 2016-11-12.
 */

public abstract class AccessGPSTask extends AsyncTask<String,Void,String>{
    //data={"user_id":19,"googlepid":"ab"}
    private int userId;
    private String googlePlaceId;

    protected AccessGPSTask(int userId, String googlePlaceId){
        this.userId = userId;
        this.googlePlaceId = googlePlaceId;
    }

    @Override
    protected String doInBackground(String... params) {
        String result = "";
        InputStream inputStream;
        try{
            URL url = new URL(NetworkManager.serverIP+"/accessByGooglePlaceID/");
            HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("user_id",userId);
            jsonObject.put("googlepid",googlePlaceId);

            String strJson = "data=" + jsonObject.toString();
            Log.d("AccessGPSTask",strJson);
            setupConnection(strJson,urlConnection);


            inputStream = urlConnection.getInputStream();

            if(inputStream != null){
                result = NetworkManager.convertInputStreamToString(inputStream);
            } else{
                result = "Did not work!";
            }
            Log.d("AccessGPSTask",result);

        } catch (Exception e){
            e.printStackTrace();
        }

        return result;
    }
}
