package com.fengyangts.jplaytext;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Message:
 * Created by  ChenLong.
 * Created by Time on 2017/11/2.
 */

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.DataHolder> {
    Context context;
    public DataAdapter(Context context) {
        this.context = context;
    }

    @Override
    public DataHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        return new DataHolder(inflate);
    }

    @Override
    public void onBindViewHolder(DataHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 30;
    }

    class DataHolder extends RecyclerView.ViewHolder{

        private final TextView dataView;

        public DataHolder(View itemView) {
            super(itemView);
            dataView = itemView.findViewById(R.id.dataView);
        }
    }
}
