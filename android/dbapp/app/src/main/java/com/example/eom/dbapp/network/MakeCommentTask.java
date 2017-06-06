package com.example.eom.dbapp.network;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import static com.example.eom.dbapp.network.NetworkManager.setupConnection;

public abstract class MakeCommentTask extends AsyncTask<String, Void, String> {
        //data={"username":"하이예나","email":"aa@aa.aa","password":"password","birth":"1994-09-16"}
        private String mComment;
        private int mUser_id,mPlace_id,mStar;
        protected MakeCommentTask(int user_id, int place_id, int star, String comment){
            mUser_id=user_id;mPlace_id = place_id; mStar=star;mComment=comment;
        }

        @Override
        protected String doInBackground(String... params) {
            InputStream inputStream;
            String strResult="";
            String result=null;
            try {
                URL url = new URL(NetworkManager.serverIP+"/evaluatePlace/");
                HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("user_id", mUser_id);
                jsonObject.put("place_id",mPlace_id);
                jsonObject.put("star",mStar);
                jsonObject.put("comment",URLEncoder.encode(mComment,"UTF-8"));
                String strJson = "data="+ jsonObject.toString();
                Log.d("getPlace", strJson);
                setupConnection(strJson,urlConnection);
                inputStream = urlConnection.getInputStream();

                if (inputStream != null)
                    strResult = NetworkManager.convertInputStreamToString(inputStream);
                else
                    strResult = "Did not work!";
                Log.d("MakeCommentTask", strResult);

            } catch (Exception e) {
                e.printStackTrace();
            }
            return strResult;
        }
    }