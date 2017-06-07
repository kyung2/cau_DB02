package com.example.eom.dbapp.network;

import android.os.AsyncTask;
import android.util.Log;

import com.example.eom.dbapp.vo.KidsCafeData;
import com.example.eom.dbapp.vo.PreSchoolData;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

import static com.example.eom.dbapp.network.NetworkManager.setupConnection;

public abstract class KidsCafeBysidoTask extends AsyncTask<String, Void, ArrayList<KidsCafeData>> {
    //data={"user_id":19,"longitude":126.95,"latitude":37.50,"range":1}37.50,126.95
    private String si_do;

    protected KidsCafeBysidoTask(String si_do) {
        this.si_do = si_do;
    }

    @Override
    protected ArrayList<KidsCafeData> doInBackground(String... params) {
        InputStream inputStream;
        String strResult = "";
        String result = null;
        ArrayList<KidsCafeData> items = new ArrayList<>();
        try {
            URL url = new URL(NetworkManager.serverIP + "/kidsCafe/sido/");
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("si_do", URLEncoder.encode(si_do, "UTF-8"));
            String strJson = "data=" + jsonObject.toString();
            Log.d("KidsCafeBysidoTask", strJson);
            setupConnection(strJson, urlConnection);
            inputStream = urlConnection.getInputStream();

            if (inputStream != null)
                strResult = NetworkManager.convertInputStreamToString(inputStream);
            else
                strResult = "Did not work!";
            Log.d("KidsCafeBysidoTask", strResult);
            JSONArray jsonArray = new JSONObject(strResult).getJSONArray("kidscafe");

            for(int i=0;i<jsonArray.length();i++) {
                JSONObject row = jsonArray.getJSONObject(i);
//values('id','name','si_do','si_gun_gu','tel','latitude','longitude')
                items.add(new KidsCafeData(row.getInt("id"),
                                row.getString("name"),
                                row.getString("si_do"),
                                row.getString("si_gun_gu"),
                                "",
                                0,
                                0)
                                );
            }
            return items;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return items;
    }
}