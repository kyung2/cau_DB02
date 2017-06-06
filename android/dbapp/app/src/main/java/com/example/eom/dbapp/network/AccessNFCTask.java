package com.example.eom.dbapp.network;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import static com.example.eom.dbapp.network.NetworkManager.setupConnection;

public abstract class AccessNFCTask extends AsyncTask<String, Void, String> {
        //data={"username":"하이예나","email":"aa@aa.aa","password":"password","birth":"1994-09-16"}
        private String mTagName;
        private int mTag_id,mUser_id;
        protected AccessNFCTask(String tagName, int tag_id, int user_id){
            mTagName=tagName; mTag_id=tag_id; mUser_id=user_id;
        }

        @Override
        protected String doInBackground(String... params) {
            InputStream inputStream;
            String strResult="";
            String result=null;
            try {
                URL url = new URL(NetworkManager.serverIP+"/accessByNFCTags/");
                HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("tagname",mTagName);
                jsonObject.put("tag_id",mTag_id);
                jsonObject.put("user_id",mUser_id);
                String strJson = "data="+ jsonObject.toString();
                Log.d("AccessNFCTask", strJson);
                setupConnection(strJson,urlConnection);
                inputStream = urlConnection.getInputStream();

                if (inputStream != null)
                    strResult = NetworkManager.convertInputStreamToString(inputStream);
                else
                    strResult = "Did not work!";
                Log.d("AccessNFCTask", strResult);

            } catch (Exception e) {
                e.printStackTrace();
            }
            return strResult;
        }
    }