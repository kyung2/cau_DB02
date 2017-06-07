package com.example.eom.dbapp.network;

import android.os.AsyncTask;
import android.util.Log;

import com.example.eom.dbapp.MyUtil;
import com.example.eom.dbapp.vo.KidsCafeData;
import com.example.eom.dbapp.vo.KidsCenterData;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;

import static com.example.eom.dbapp.network.NetworkManager.setupConnection;

public abstract class KidsCenterDetailTask extends AsyncTask<String, Void, KidsCenterData> {
    //data={"user_id":19,"longitude":126.95,"latitude":37.50,"range":1}37.50,126.95
    private int id;

    protected KidsCenterDetailTask(int id) {
        this.id = id;
    }

    @Override
    protected KidsCenterData doInBackground(String... params) {
        InputStream inputStream;
        String strResult = "";
        String result = null;
        KidsCenterData item = null;
        try {
            URL url = new URL(NetworkManager.serverIP + "/kidsCenter/detail/");
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", id);
            String strJson = "data=" + jsonObject.toString();
            Log.d("KidsCenterDetailTask", strJson);
            setupConnection(strJson, urlConnection);
            inputStream = urlConnection.getInputStream();

            if (inputStream != null)
                strResult = NetworkManager.convertInputStreamToString(inputStream);
            else
                strResult = "Did not work!";
            Log.d("KidsCenterDetailTask", strResult);
            JSONObject jsonObject1 = new JSONArray(new JSONObject(strResult).getString("kidscenter")).getJSONObject(0).getJSONObject("fields");

            //데이터는 {\"model\": \"dbTeam.kidscafe\", \"pk\": 1, \"fields\": {\"name\": \"\\uc544\\uac00\\uc57c\", \"permit_date\": \"2012-08-24\", \"postcode\": 412827, \"si_do\": \"\\uacbd\\uae30\\ub3c4\", \"si_gun_gu\": \"\\uace0\\uc591\\uc2dc\", \"address\": \"\\uc6b4\\uc601\\uc911\", \"operation_state\": \"\\uacbd\\uae30\\ub3c4 \\uace0\\uc591\\uc2dc \\ub355\\uc591\\uad6c\", \"multiple_use\": \"\", \"facility_size\": 0.0, \"longitude\": 126.8306735, \"latitude\": 37.6318879}
            Log.d("KidsCenterDetailTask",jsonObject1.getString("name"));// 데이터 위에에서 뽑으면 이렇게 나옴 (이건 name)임
            //TODO 이거 밑에 preSchoolData 파싱해줘 id는 그냥 id 라고 해줘줘
            //그다음에 이거 위에 에서 파싱해서 하나씩 뽑아서 밑에 프리스쿨데이터에 넣어주면됨.
            //Date같은경우는
            Date tempDate = MyUtil.getDateFromString("1994-09-23");
            //d위에처럼 해서 밑에 데이터에 집어넣으면됨
           //item = new PreSchoolData(
            //
            // )

            return item;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return KidsCenterData.getSampleKidsCenterData();
    }
}