package com.example.eom.dbapp.network;

import android.location.Location;
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

public abstract class PreSchoolsByGPSTask extends AsyncTask<String, Void, ArrayList<PreSchoolData>> {
    //data={"user_id":19,"longitude":126.95,"latitude":37.50,"range":1}37.50,126.95
    private int mUserID;
    private double latitude,longitude;
    private int range;

    protected PreSchoolsByGPSTask(double latitude, double longitude, int userID,int range) {
        mUserID = userID;
        this.latitude = latitude;
        this.longitude = longitude;
        this.range = range;
    }

    @Override
    protected ArrayList<PreSchoolData> doInBackground(String... params) {
        InputStream inputStream;
        String strResult = "";
        String result = null;
        ArrayList<PreSchoolData> items = new ArrayList<>();
        Location myLocation = new Location("mine");
        myLocation.setLatitude(latitude);
        myLocation.setLongitude(longitude);
        try {
            URL url = new URL(NetworkManager.serverIP + "/getMyNearPlace/");
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("user_id", mUserID);
            jsonObject.put("latitude", latitude);
            jsonObject.put("longitude", longitude);
            jsonObject.put("range", range);
            String strJson = "data=" + jsonObject.toString();
            Log.d("PlaceListByGPSTask", strJson);
            setupConnection(strJson, urlConnection);
            inputStream = urlConnection.getInputStream();

            if (inputStream != null)
                strResult = NetworkManager.convertInputStreamToString(inputStream);
            else
                strResult = "Did not work!";
            Log.d("PreSchoolsByGPSTask", strResult);
            JSONArray jsonArray = new JSONArray(strResult);

            for(int i=0;i<jsonArray.length();i++) {
                JSONObject row = jsonArray.getJSONObject(i);
                Location location = new Location("Ss");
                location.setLatitude(row.getDouble("latitude"));
                location.setLongitude(row.getDouble("longitude"));
                // 밑에부분이 서버에서 불러온 값으로 리스트 만드는 코드
//                if(myLocation.distanceTo(location)<500*range)
//                    items.add(new PreSchoolData(
//                            0,
//                            row.getString("name"),
//                            row.getDouble("star"),
//                            row.getInt("method"),
//                            row.getInt("exp"),
//                            row.getBoolean("visited"),
//                            row.getDouble("latitude"),
//                            row.getDouble("longitude"),
//                            row.getInt("radius"),
//                            row.getString("googlepid"),
//                            row.getInt("place_id")));
            }
            return items;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return items;
    }
}