package com.happycity.project.jobme.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.happycity.project.jobme.R;
import com.happycity.project.jobme.common.ActivityUtils;

import java.io.InputStream;
import java.net.MalformedURLException;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawer;
    NavigationView navigationView;
    ActionBarDrawerToggle toggle;
    View headerView;

    TextView txtUserName, txtUserEmail;
    ImageView imgAvatarUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
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
        initView();
        getDataFromLoginAndSetToNavigationView();
    }

    private void initView() {
        txtUserName = findViewById(R.id.txtUserName);
        txtUserEmail = findViewById(R.id.txtUserEmail);
        imgAvatarUser = findViewById(R.id.imgAvatarUser);
    }

    @SuppressLint("SetTextI18n")
    private void getDataFromLoginAndSetToNavigationView() {
        Intent intent = getIntent();
        if (intent.hasExtra(ActivityUtils.HOME_KEY_PUT_EXTRA)){
            Bundle extras = intent.getExtras();
            String user_name = null;
            String url_avatar = null;
            if (extras != null) {
                user_name = extras.getString(ActivityUtils.USER_NAME);
                url_avatar = extras.getString(ActivityUtils.USER_AVATAR);
            }
//          String user_email = extras.get(ActivityUtils.USER_AVATAR).toString();
            txtUserName.setText(user_name + "");
            new GetUserAvatar(imgAvatarUser).execute(url_avatar);
        }else{
            throw new IllegalArgumentException("Activity cannot find  extras " + ActivityUtils.HOME_KEY_PUT_EXTRA);
        }
    }

    @SuppressLint("StaticFieldLeak")
    public class GetUserAvatar extends AsyncTask<String, Void, Bitmap>{
        ImageView mImageBitMap;
        public GetUserAvatar(ImageView imageView){
            this.mImageBitMap = imageView;
        }

        @Override
        protected Bitmap doInBackground(String... urls) {
            String urlDisplay = urls[0];
            Bitmap mIcon = null;
            try{
                InputStream in = new java.net.URL(urlDisplay).openStream();
                mIcon = BitmapFactory.decodeStream(in);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (Exception e) {
                Log.e("ERROR", e.getMessage());
                e.printStackTrace();
            }
            return mIcon;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            mImageBitMap.setImageBitmap(bitmap);
        }
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
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
