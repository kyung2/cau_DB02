package com.example.eom.dbapp.network;

import android.os.AsyncTask;
import android.util.Log;

import com.example.eom.dbapp.vo.KidsCafeData;
import com.example.eom.dbapp.vo.KidsCenterData;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import static com.example.eom.dbapp.network.NetworkManager.setupConnection;

public abstract class KidsCenterByGPSTask extends AsyncTask<String, Void, ArrayList<KidsCenterData>> {
    //data={"user_id":19,"longitude":126.95,"latitude":37.50,"range":1}37.50,126.95
    private double latitude,longitude;

    protected KidsCenterByGPSTask(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    @Override
    protected ArrayList<KidsCenterData> doInBackground(String... params) {
        InputStream inputStream;
        String strResult = "";
        String result = null;
        ArrayList<KidsCenterData> items = new ArrayList<>();
        try {
            URL url = new URL(NetworkManager.serverIP + "/kidsCenter/gps/");
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("latitude", latitude);
            jsonObject.put("longitude", longitude);
            String strJson = "data=" + jsonObject.toString();
            Log.d("PlaceListByGPSTask", strJson);
            setupConnection(strJson, urlConnection);
            inputStream = urlConnection.getInputStream();

            if (inputStream != null)
                strResult = NetworkManager.convertInputStreamToString(inputStream);
            else
                strResult = "Did not work!";
            Log.d("PreSchoolsByGPSTask", strResult);
            JSONArray jsonArray = new JSONObject(strResult).getJSONArray("kidscenter");

            for(int i=0;i<jsonArray.length();i++) {
                JSONObject row = jsonArray.getJSONObject(i);
                //values('id', 'name', 'si_do', 'si_gun_gu', 'tel', 'latitude', 'longitude')
                items.add(new KidsCenterData(row.getInt("id"),
                                row.getString("name"),
                                row.getString("si_do"),
                                row.getString("si_gun_gu"),
                                row.getString("tel"),
                                row.getDouble("latitude"),
                                row.getDouble("longitude"))
                                );
            }
            return items;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return items;
    }
}