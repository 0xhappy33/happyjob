package com.happycity.project.jobme.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.happycity.project.jobme.R;

/**
 * Created by Ha Truong on 2017/08/03.
 */

public class JobsAdapter extends RecyclerView.Adapter<JobsAdapter.ViewHolder> {

    private String[] titles;

    public JobsAdapter(String[] titles) {
        this.titles = titles;
    }

    @Override
    public JobsAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_jobs, viewGroup, false);
        return new JobsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(JobsAdapter.ViewHolder holder, int position) {
        String title = titles[position];
        holder.title.setText(title);
    }

    @Override
    public int getItemCount() {
        return titles.length;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView title;

        ViewHolder(final View itemView) {
            super(itemView);
            this.title = (TextView) itemView.findViewById(R.id.title);
        }
    }
}