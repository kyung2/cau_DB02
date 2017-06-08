package com.example.eom.dbapp.ListActivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import com.example.eom.dbapp.Adapter.IdAndStringListAdapter;
import com.example.eom.dbapp.R;
import com.example.eom.dbapp.network.ListByGPSTask;
import com.example.eom.dbapp.vo.IdAndString;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by hyunkyung on 2017-06-08.
 */

public class TrafficAccidentAreaListActivity extends AppCompatActivity {
    IdAndStringListAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kidscenter_list);

        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.rv_kidscenter);
        final ArrayList<IdAndString> arrayList = new ArrayList<>();
        adapter = new IdAndStringListAdapter(this,arrayList);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        new ListByGPSTask(this,"/trafficAccidentArea/gps/","trafficaccidentarea"){
            @Override
            protected void onPostExecute(JSONArray jsonArray) {
                super.onPostExecute(jsonArray);
                //     values('id', 'si_do', 'si_gun_gu', 'law_code',"near_school", 'latitude', 'longitude')[:20]
                for (int i = 0; i < jsonArray.length(); i++) {
                    try {
                        JSONObject row = jsonArray.getJSONObject(i);
                        arrayList.add(new IdAndString(row.getInt("id"),

                                row.getString("name")
                                        + row.getString("si_do")
                                        + row.getString("si_gun_gu")
                                        + ""+ row.getInt("law_code")
                                        + row.getString("near_school")
                                        + "" + row.getDouble("latitude")
                                        + "" + row.getDouble("longitude"), IdAndString.TrafficAccidentAreaListActivity)
                        );
                    } catch (Exception e) {

                    }
                }
                adapter.notifyDataSetChanged();
            }
        }.execute("");
    }
}

