package com.example.eom.dbapp.ListActivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import com.example.eom.dbapp.Adapter.IdAndStringListAdapter;
import com.example.eom.dbapp.R;
import com.example.eom.dbapp.network.ListByGPSTask;
import com.example.eom.dbapp.vo.IdAndString;

import org.json.JSONArray;

import java.util.ArrayList;

/**
 * Created by hyunkyung on 2017-06-08.
 */

public class SafeAreaListActivity extends AppCompatActivity {
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

        new ListByGPSTask(this,"/safeArea/gps/","safearea"){
            @Override
            protected void onPostExecute(JSONArray jsonArray) {
                super.onPostExecute(jsonArray);

            }
        }.execute("");
    }
}
