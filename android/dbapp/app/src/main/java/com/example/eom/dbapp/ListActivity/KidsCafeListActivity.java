package com.example.eom.dbapp.ListActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.eom.dbapp.Adapter.KidsCafeAdapter;
import com.example.eom.dbapp.MySharedPreferences;
import com.example.eom.dbapp.R;
import com.example.eom.dbapp.network.KidsCafeByGPSTask;
import com.example.eom.dbapp.network.KidsCafeBysidoTask;
import com.example.eom.dbapp.network.KidsCafeBysignguTask;
import com.example.eom.dbapp.vo.KidsCafeData;

import java.util.ArrayList;

public class KidsCafeListActivity extends AppCompatActivity {
    KidsCafeAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kidscafe_list);
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.rv_kidscafe);
        final ArrayList<KidsCafeData> arrayList = new ArrayList<>();
        RecyclerView.ItemDecoration itemDecoration =
                new DividerItemDecoration(this, LinearLayoutManager.VERTICAL);
        recyclerView.addItemDecoration(itemDecoration);

        adapter = new KidsCafeAdapter(this,arrayList);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        SharedPreferences sp = getSharedPreferences(MySharedPreferences.NAME,0);
        Double latitude = Double.parseDouble(sp.getString(MySharedPreferences.USER_LATITUDE,"35"));
        Double longitude = Double.parseDouble(sp.getString(MySharedPreferences.USER_LONGITUDE,"123"));
        findViewById(R.id.bt_kidcafe_list_search).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String si_do = ((EditText)findViewById(R.id.et_detail_kidscafe_si_input)).getText().toString();
                String si_gun = ((EditText)findViewById(R.id.et_detail_kidscafe_sigungu_input)).getText().toString();
                if(si_do.equals("")){
                    Toast.makeText(KidsCafeListActivity.this,"시 도를 입력해주세요",Toast.LENGTH_SHORT).show();
                }else if (si_gun.equals("")) {
                    Toast.makeText(KidsCafeListActivity.this, "시 군 구를 입력해주세요", Toast.LENGTH_SHORT).show();
                    new KidsCafeBysidoTask(si_do){
                        @Override
                        protected void onPostExecute(ArrayList<KidsCafeData> kidsCafeDatas) {
                            super.onPostExecute(kidsCafeDatas);
                            arrayList.clear();
                            arrayList.addAll(kidsCafeDatas);
                            adapter.notifyDataSetChanged();
                        }
                    }.execute("");
                }else{
                    Toast.makeText(KidsCafeListActivity.this,"잘했어요",Toast.LENGTH_SHORT).show();
                    new KidsCafeBysignguTask(si_do,si_gun){
                        @Override
                        protected void onPostExecute(ArrayList<KidsCafeData> kidsCafeDatas) {
                            super.onPostExecute(kidsCafeDatas);
                            arrayList.clear();
                            arrayList.addAll(kidsCafeDatas);
                            adapter.notifyDataSetChanged();
                        }
                    }.execute("");
                }
            }
        });
        new KidsCafeByGPSTask(latitude,longitude){
            @Override
            protected void onPostExecute(ArrayList<KidsCafeData> kidsCenterDatas) {
                super.onPostExecute(kidsCenterDatas);
                arrayList.clear();
                arrayList.addAll(kidsCenterDatas);
                adapter.notifyDataSetChanged();
            }
        }.execute("");
    }
}
