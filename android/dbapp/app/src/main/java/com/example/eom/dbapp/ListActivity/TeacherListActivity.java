package com.example.eom.dbapp.ListActivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.eom.dbapp.Adapter.TeacherAdpater;
import com.example.eom.dbapp.R;
import com.example.eom.dbapp.vo.TeacherData;

import java.util.ArrayList;

public class TeacherListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_list);
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.rv_teacher);

        ArrayList<TeacherData> arrayList = new ArrayList<>();
        RecyclerView.ItemDecoration itemDecoration =
                new DividerItemDecoration(this, LinearLayoutManager.VERTICAL);
        recyclerView.addItemDecoration(itemDecoration);

        for(int i=0 ;i<30;i++){
            arrayList.add(new TeacherData("티이쳐어"+i));
        }
        TeacherAdpater adapter = new TeacherAdpater(this,arrayList);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }
}
