package com.example.eom.dbapp.network;

import android.os.AsyncTask;
import android.util.Log;

import com.example.eom.dbapp.vo.PreSchoolData;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import static com.example.eom.dbapp.network.NetworkManager.setupConnection;

public abstract class PreSchoolsDetailTask extends AsyncTask<String, Void, PreSchoolData> {
    //data={"user_id":19,"longitude":126.95,"latitude":37.50,"range":1}37.50,126.95
    private int id;

    protected PreSchoolsDetailTask(int id) {
        this.id = id;
    }

    @Override
    protected PreSchoolData doInBackground(String... params) {
        InputStream inputStream;
        String strResult = "";
        String result = null;
        PreSchoolData item = null;
        try {
            URL url = new URL(NetworkManager.serverIP + "/preschool/detail/");
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", id);
            String strJson = "data=" + jsonObject.toString();
            Log.d("PlaceListByGPSTask", strJson);
            setupConnection(strJson, urlConnection);
            inputStream = urlConnection.getInputStream();

            if (inputStream != null)
                strResult = NetworkManager.convertInputStreamToString(inputStream);
            else
                strResult = "Did not work!";
            Log.d("PreSchoolsByGPSTask", strResult);
            JSONObject jsonObject1 = new JSONObject(strResult).getJSONObject("preschool");
            //TODO 이거 밑에 preSchoolData 파싱해줘
            //item = new PreSchoolData()

            return item;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return PreSchoolData.getSamplePreschoolData();
    }
}