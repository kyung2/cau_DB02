package com.example.eom.dbapp.network;

import android.os.AsyncTask;
import android.util.Log;

import com.example.eom.dbapp.MyUtil;
import com.example.eom.dbapp.vo.PreSchoolData;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;

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
            Log.d("PreschoolDetailTask", strJson);
            setupConnection(strJson, urlConnection);
            inputStream = urlConnection.getInputStream();

            if (inputStream != null)
                strResult = NetworkManager.convertInputStreamToString(inputStream);
            else
                strResult = "Did not work!";
            Log.d("PreschoolDetailTask", strResult);
            JSONObject jsonObject1 = new JSONArray(new JSONObject(strResult).getString("preschool")).getJSONObject(0).getJSONObject("fields");

            //데이터는 {\"name\": \"\\uac00\\ud68c\\uc5b4\\ub9b0\\uc774\\uc9d1\", \"type\": \"\\uad6d\\uacf5\\ub9bd\", \"permit_date\": \"1995-06-23\", \"pause_end_date\": null, \"close_date\": null, \"postcode\": 3056, \"si_do\": \"\\uc11c\\uc6b8\\ud2b9\\ubcc4\\uc2dc\", \"si_gun_gu\": \"\\uc885\\ub85c\\uad6c\", \"address\": \"\\uc11c\\uc6b8\\ud2b9\\ubcc4\\uc2dc \\uc885\\ub85c\\uad6c \\ubd81\", \"latitude\": 37.58180502, \"longitude\": 126.9858807, \"operation_state\": \"\\uc815\\uc0c1\", \"tel\": \"02-3673-208\", \"Fax\": \"02-3673-579\", \"capacity\": 49, \"school_bus\": \"\\ubbf8\\uc6b4\\uc601\", \"playground_num\": 1, \"nursing_room_num\": 4, \"nursing_room_area\": 250, \"assess_certification_type\": 1, \"NumofCCTV\": 2}
            Log.d("PreschoolDetailTask",jsonObject1.getString("name"));// 데이터 위에에서 뽑으면 이렇게 나옴 (이건 name)임
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

        return PreSchoolData.getSamplePreschoolData();
    }
}