package com.rongyi.myapplication;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by rongyi on 2017/7/31.
 */

public class DaoAdapter extends RecyclerView.Adapter<DaoAdapter.DaoAdapterHolder> {

    private Context mContext;
    private List<StudentMsgBean> mDates;

    public DaoAdapter(Context context, List<StudentMsgBean> dates) {
        mContext = context;
        mDates = dates;
    }

    @Override
    public DaoAdapterHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(mContext).inflate(R.layout.item_home, null);
        return new DaoAdapterHolder(root);

    }

    @Override
    public void onBindViewHolder(DaoAdapterHolder holder, int position) {

        holder.stuId.setText(mDates.get(position).getId() + "");
        holder.studentName.setText(mDates.get(position).getStudentNum());
        holder.stuName.setText(mDates.get(position).getName());
    }

    @Override
    public int getItemCount() {
        if (mDates != null) {
            return mDates.size();
        }

        return 0;

    }

    public class DaoAdapterHolder extends RecyclerView.ViewHolder {

        private TextView stuId;
        private TextView studentName;
        private TextView stuName;


        public DaoAdapterHolder(View itemView) {
            super(itemView);

            stuId = (TextView) itemView.findViewById(R.id.stuId);
            studentName = (TextView) itemView.findViewById(R.id.studentName);
            stuName = (TextView) itemView.findViewById(R.id.stuName);


        }

    }

}
