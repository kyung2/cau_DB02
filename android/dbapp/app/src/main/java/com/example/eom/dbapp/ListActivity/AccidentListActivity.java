package com.example.eom.dbapp.ListActivity;

import android.graphics.drawable.Drawable;
        import android.os.Bundle;
        import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

import com.example.eom.dbapp.Adapter.IdAndStringListAdapter;
        import com.example.eom.dbapp.R;
        import com.example.eom.dbapp.network.ListByGPSTask;
        import com.example.eom.dbapp.vo.IdAndString;

        import org.json.JSONArray;

        import java.util.ArrayList;

        import static java.security.AccessController.getContext;

/**
 * Created by hyunkyung on 2017-06-08.
 */




public class AccidentListActivity extends AppCompatActivity {
    DividerItemDecoration divider = new DividerItemDecoration(getApplicationContext(),new LinearLayoutManager(this).getOrientation());

    IdAndStringListAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kidscenter_list);

        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.rv_kidscenter);
        final ArrayList<IdAndString> arrayList = new ArrayList<>();
        RecyclerView.ItemDecoration itemDecoration =
                new DividerItemDecoration(this, LinearLayoutManager.VERTICAL);
        recyclerView.addItemDecoration(itemDecoration);

        adapter = new IdAndStringListAdapter(this,arrayList);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();


    }
}

