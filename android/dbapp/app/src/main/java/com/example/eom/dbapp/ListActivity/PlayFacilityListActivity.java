package com.example.eom.dbapp.ListActivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
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

public class PlayFacilityListActivity extends AppCompatActivity {
    IdAndStringListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kidscenter_list);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv_kidscenter);
        final ArrayList<IdAndString> arrayList = new ArrayList<>();
        RecyclerView.ItemDecoration itemDecoration =
                new DividerItemDecoration(this, LinearLayoutManager.VERTICAL);
        recyclerView.addItemDecoration(itemDecoration);
        adapter = new IdAndStringListAdapter(this, arrayList);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        new ListByGPSTask(this, "/playFacility/gps/", "playfacility") {
            @Override
            protected void onPostExecute(JSONArray jsonArray) {
                super.onPostExecute(jsonArray);

                for (int i = 0; i < jsonArray.length(); i++) {
                    try {
                        JSONObject row = jsonArray.getJSONObject(i);
//values('id','name','si_do','si_gun_gu','install_place','latitude','longitude')
                        arrayList.add(new IdAndString(row.getInt("id"),

                                row.getString("name")
                                        + row.getString("si_do")
                                        + row.getString("si_gun_gu")
                                        + row.getString("install_place")
                                        + "" + row.getDouble("latitude")
                                        + "" + row.getDouble("longitude"), IdAndString.PlayFacilityListActivity)
                        );
                    } catch (Exception e) {

                    }
                }
                adapter.notifyDataSetChanged();
            }
        }.execute("");
    }
}
