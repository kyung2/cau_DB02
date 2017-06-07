package com.example.eom.dbapp;

        import android.os.Bundle;
        import android.support.v7.app.AppCompatActivity;
        import android.support.v7.widget.RecyclerView;

        import com.example.eom.dbapp.Adapter.IdAndStringListAdapter;
        import com.example.eom.dbapp.vo.IdAndString;

        import java.util.ArrayList;

/**
 * Created by hyunkyung on 2017-06-08.
 */

public class AccidentListActivity extends AppCompatActivity {
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
    }
}

