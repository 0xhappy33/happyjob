package com.happycity.project.jobme.view.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.happycity.project.jobme.R;
import com.happycity.project.jobme.data.model.Job;

import java.net.MalformedURLException;
import java.net.URL;

import butterknife.BindView;
import butterknife.ButterKnife;

public class JobDetailActivity extends AppCompatActivity implements OnMapReadyCallback {

    @BindView(R.id.imgLogoCompanyDetail)
    ImageView imgLogoCompanyDetail;
    @BindView(R.id.txtTitleCompanyDetail)
    TextView txtTitleCompanyDetail;
    @BindView(R.id.txtCompanyNameDetail)
    TextView txtCompanyNameDetail;
    @BindView(R.id.txtDescriptionDetail)
    TextView txtDescriptionDetail;
    @BindView(R.id.txtCompanyLocationDetail)
    TextView txtCompanyLocationDetail;
    @BindView(R.id.txtCompanyUrlDetail)
    TextView txtCompanyUrlDetail;
    @BindView(R.id.txtCompanyCreatedDetail)
    TextView txtCompanyCreatedDetail;

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_detail);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        initGoogleMap();
        initView();
        setData();
    }

    private void initGoogleMap() {
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    private void setData() {
        Intent intent = getIntent();
        Job job = (Job) intent.getSerializableExtra("job");
        txtTitleCompanyDetail.setText(job.getTitle());
        txtCompanyNameDetail.setText(job.getCompany());
        txtDescriptionDetail.setText(job.getDescription());
        txtCompanyLocationDetail.setText(job.getLocation());
        txtCompanyUrlDetail.setText(job.getUrl());
        txtCompanyCreatedDetail.setText(job.getCreatedAt());
        URL url = null;
        try {
            url = new URL(job.getLogo());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        Glide.with(JobDetailActivity.this).load(url).into(imgLogoCompanyDetail);
    }

    private void initView() {
//        imgLogoCompanyDetail = findViewById(R.id.imgLogoCompanyDetail);
//        txtTitleCompanyDetail = findViewById(R.id.txtTitleCompanyDetail);
//        txtCompanyNameDetail = findViewById(R.id.txtCompanyNameDetail);
//        txtDescriptionDetail = findViewById(R.id.txtDescriptionDetail);
//        txtCompanyLocationDetail = findViewById(R.id.txtCompanyLocationDetail);
//        txtCompanyUrlDetail = findViewById(R.id.txtCompanyUrlDetail);
//        txtCompanyCreatedDetail = findViewById(R.id.txtCompanyCreatedDetail);
        ButterKnife.bind(this);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
}
