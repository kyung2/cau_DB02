package com.example.eom.dbapp.network;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import static com.example.eom.dbapp.network.NetworkManager.setupConnection;

public abstract class EvaluateDataTask extends AsyncTask<String, Void, String> {
        //data={"username":"하이예나","email":"aa@aa.aa","password":"password","birth":"1994-09-16"}
        private int mPlace_id, mIndex;
        protected EvaluateDataTask(int place_id, int index){
            mPlace_id = place_id; mIndex=index;
        }

        @Override
        protected String doInBackground(String... params) {
            InputStream inputStream;
            String strResult="";
            String result=null;
            try {
                URL url = new URL(NetworkManager.serverIP+"/getEvaluateDataWithIndex/");
                HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("place_id",mPlace_id);
                jsonObject.put("index",mIndex);
                String strJson = "data="+ jsonObject.toString();
                Log.d("EvaluateDataTask", strJson);
                setupConnection(strJson,urlConnection);
                inputStream = urlConnection.getInputStream();

                if (inputStream != null)
                    strResult = NetworkManager.convertInputStreamToString(inputStream);
                else
                    strResult = "Did not work!";
                Log.d("EvaluateDataTask", strResult);

            } catch (Exception e) {
                e.printStackTrace();
            }
            return strResult;
        }
    }