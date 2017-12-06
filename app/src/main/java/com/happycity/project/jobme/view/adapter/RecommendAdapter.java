package com.happycity.project.jobme.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.happycity.project.jobme.R;
import com.happycity.project.jobme.data.model.Job;

import java.util.Collections;
import java.util.List;

/**
 * Created by Ha Truong on 12/6/2017.
 * This is a jobme
 * into the com.happycity.project.jobme.view.adapter
 */

public class RecommendAdapter extends RecyclerView.Adapter<RecommendAdapter.ViewHolder>{

    private List<Job> mJobList = Collections.emptyList();
    private LayoutInflater mInflater;
    private ItemClickListener mListener;
    private Context mContext;

    public RecommendAdapter(Context context, List<Job> mJobList) {
        this.mContext = context;
        this.mJobList = mJobList;
        this.mInflater = LayoutInflater.from(context);
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_recommend_jobs, parent, false);
        return new ViewHolder(view);
    }

    // binds data to each view on row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String logo = mJobList.get(position).getLogo();
        String name = mJobList.get(position).getCompany();

        holder.name_company_recommend.setText(name);
        Glide.with(mContext).load(logo).into(holder.img_company_recommend);
    }

    // total number of row
    @Override
    public int getItemCount() {
        return mJobList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView img_company_recommend;
        TextView name_company_recommend;
        ViewHolder(View itemView) {
            super(itemView);
            img_company_recommend = itemView.findViewById(R.id.img_company_recommend);
            name_company_recommend = itemView.findViewById(R.id.name_company_recommend);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mListener != null){
                mListener.onItemClick(view, getAdapterPosition());
            }
        }
    }


    public void setItemListener(ItemClickListener itemListener){
        this.mListener = itemListener;
    }
}
