package com.example.eom.dbapp.Adapter;

        import android.content.Context;
        import android.support.v7.widget.RecyclerView;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ImageButton;
        import android.widget.TextView;
        import android.widget.Toast;

        import com.example.eom.dbapp.R;
        import com.example.eom.dbapp.vo.KidsCafeData;
        import com.example.eom.dbapp.vo.PreSchoolData;

        import java.util.ArrayList;

/**
 * Created by dhtpr on 2017-06-05.
 */

public class KidsCafeAdpater extends RecyclerView.Adapter<KidsCafeAdpater.ViewHolder> {

    Context context;
    ArrayList<KidsCafeData> items;
    int item_layout;

    public KidsCafeAdpater(Context context, ArrayList<KidsCafeData> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_kidscafe_list_item, parent, false);
        return new ViewHolder(v);
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final KidsCafeData item = items.get(position);
        holder.tv_name.setText(item.name);
        holder.tv_address.setText(item.address);
        holder.bt_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "전화버튼이 눌렸음", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.items.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv_name;
        TextView tv_address;
        ImageButton bt_call;

        ViewHolder(View itemView) {
            super(itemView);
            tv_name = (TextView) itemView.findViewById(R.id.tv_kidscafe_list_name);
            tv_address = (TextView) itemView.findViewById(R.id.tv_kidscafe_list_address);
            bt_call = (ImageButton) itemView.findViewById(R.id.bt_kidscafe_list_call);
        }
    }
}