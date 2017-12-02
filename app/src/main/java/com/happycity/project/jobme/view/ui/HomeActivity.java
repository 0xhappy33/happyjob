package com.happycity.project.jobme.view.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.happycity.project.jobme.R;
import com.happycity.project.jobme.data.api.Constants;
import com.happycity.project.jobme.data.model.Job;
import com.happycity.project.jobme.view.adapter.JobsAdapter;
import com.happycity.project.jobme.view.utils.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawer;
    NavigationView navigationView;
    ActionBarDrawerToggle toggle;
    View headerView;

    EditText search;
    ListView rvJobList;
    ArrayList<Job> jobArrayList;
    JobsAdapter jobsAdapter;
    String id=" ",logo = "", title ="",
            type="", company ="",
            description="", urls="",
            location="", createAt="";

    String url = Server.jobGithub;


    TextView txtUserName, txtUserEmail;
    ImageView imgAvatarUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setupActionBar();
        initView();
        setDataToTextView();
        getJobListFromServer();
        addTextChangeListener();
    }

    @SuppressLint("SetTextI18n")
    private void setDataToTextView() {
        String name = getIntent().getStringExtra("name");
        String email = getIntent().getStringExtra("email");
        String urlImage = getIntent().getStringExtra("url");
        try {
            // create url to url that get from login activity
            URL url = new URL(urlImage);
            Glide.with(HomeActivity.this).load(url).into(imgAvatarUser);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        txtUserName.setText(name + "");
        txtUserEmail.setText(email + "");
    }

    private void addTextChangeListener() {
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence query, int start, int before, int count) {
                query = query.toString().toLowerCase();
                final ArrayList<Job> filtered = new ArrayList<>();
                for (int i = 0 ; i < jobArrayList.size(); i ++){
                    final String text = jobArrayList.get(i).getTitle().toLowerCase();
                    if (text.contains(query)){
                        filtered.add(jobArrayList.get(i));
                    }
                }

                jobsAdapter = new JobsAdapter(filtered, getApplicationContext());
                rvJobList.setAdapter(jobsAdapter);
                jobsAdapter.notifyDataSetChanged();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void getJobListFromServer() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        final JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if (response != null){
                    for (int i = 0;i<response.length();i++){
                        try {
                            JSONObject jsonObject = response.getJSONObject(i);
                            id = jsonObject.getString(Constants.TAG_ID);
                            title = jsonObject.getString(Constants.TAG_TITLE);
                            location = jsonObject.getString(Constants.TAG_LOCATION);
                            logo = jsonObject.getString(Constants.TAG_LOGO);
                            company = jsonObject.getString(Constants.TAG_COMPANY);
                            description = jsonObject.getString(Constants.TAG_DESCRIPTION);
                            createAt = jsonObject.getString(Constants.TAG_CREATE_AT);
                            type = jsonObject.getString(Constants.TAG_TYPE);
                            urls = jsonObject.getString(Constants.TAG_URL);
                            Job job = new Job(
                                    title,
                                    location,
                                    type,
                                    description.substring(0,80) + "...",
                                    company,
                                    urls,
                                    logo,
                                    createAt
                            );
                            jobArrayList.add(job);
                            jobsAdapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(HomeActivity.this, "Sorry", Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(jsonArrayRequest);
    }


    // initial view
    public void initView(){
        txtUserName = headerView.findViewById(R.id.txtUserName);
        txtUserEmail = headerView.findViewById(R.id.txtUserEmail);
        imgAvatarUser = headerView.findViewById(R.id.imgAvatarUser);
        search = findViewById(R.id.search);
        rvJobList = findViewById(R.id.recyclerViewJob);
        jobArrayList = new ArrayList<>();
        jobsAdapter = new JobsAdapter(jobArrayList, getApplicationContext());
        rvJobList.setAdapter(jobsAdapter);
    }

    public void setupActionBar(){
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        headerView = navigationView.getHeaderView(0);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        //MenuItem mSearchMenu = menu.findItem(R.id.action_search);
        //searchView = (SearchView) MenuItemCompat.getActionView(mSearchMenu);
        //filterForResults(searchView);

        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_search) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_job) {

        } else if (id == R.id.nav_about) {

        }  else if (id == R.id.nav_notification) {

        }  else if (id == R.id.nav_logout) {

        }


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
