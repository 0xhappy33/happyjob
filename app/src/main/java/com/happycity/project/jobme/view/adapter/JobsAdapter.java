package com.happycity.project.jobme.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.happycity.project.jobme.R;
import com.happycity.project.jobme.data.model.Job;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Ha Truong on 11/23/2017.
 * This is a jobme
 * into the com.happycity.project.jobme.view.adapter
 */

public class JobsAdapter extends BaseAdapter{

    private ArrayList<Job> jobArrayList;
    private Context context;


    public JobsAdapter(ArrayList<Job> listJob, Context context) {
        this.jobArrayList = listJob;
        this.context = context;
    }


    @Override
    public int getCount() {
        return jobArrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return jobArrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public class ViewHolder{
        ImageView imgLogo;
        TextView txtTitle, txtType, txtCompanyName, txtDescription, txtUrl, txtLocation, txtCreateAt;

    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if (view == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_jobs, null);
            viewHolder.imgLogo = view.findViewById(R.id.logoCompany);
            viewHolder.txtTitle = view.findViewById(R.id.txtTitle);
            viewHolder.txtType = view.findViewById(R.id.txtType);
            viewHolder.txtCompanyName = view.findViewById(R.id.txtCompanyName);
            viewHolder.txtDescription  = view.findViewById(R.id.descriptionCompany);
            viewHolder.txtUrl  = view.findViewById(R.id.urlCompany);
            viewHolder.txtLocation = view.findViewById(R.id.txtLocation);
            viewHolder.txtCreateAt = view.findViewById(R.id.txtCreateAt);
            view.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) view.getTag();
        }

        Job job = (Job) getItem(position);
        viewHolder.txtTitle.setText(job.getTitle());
        viewHolder.txtType.setText(job.getType());
        viewHolder.txtCompanyName.setText(job.getCompany());
        viewHolder.txtDescription.setText(job.getDescription());
        viewHolder.txtUrl.setText(job.getUrl());
        viewHolder.txtLocation.setText(job.getLocation());
        viewHolder.txtCreateAt.setText(job.getCreatedAt());
        Picasso.with(context).load(job.getLogo())
                .placeholder(R.drawable.repairing)
                .error(R.drawable.repairing)
                .into(viewHolder.imgLogo);
        return view;
    }

}