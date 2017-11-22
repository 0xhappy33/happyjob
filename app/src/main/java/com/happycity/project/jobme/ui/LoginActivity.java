package com.happycity.project.jobme.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.happycity.project.jobme.R;
import com.happycity.project.jobme.common.ActivityUtils;

public class LoginActivity extends AppCompatActivity{

    CallbackManager callbackManager;
    LoginButton loginButton;
    AccessTokenTracker accessTokenTracker;
    ProfileTracker profileTracker;

    String userName="", userEmail="", userID="";

    Bundle bundle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_login);
        callbackManager = CallbackManager.Factory.create();
        initFacebook();
        init();
        addEvents();

    }

    private void addEvents() {
        FacebookCallback<LoginResult> callback = new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Profile profile = Profile.getCurrentProfile();
                goToHomePage(profile);
                Toast.makeText(LoginActivity.this, "Login in ....", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        };
        loginButton.setReadPermissions("user_friends");
        loginButton.registerCallback(callbackManager, callback);
    }

    private void initFacebook() {
        accessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldToken, AccessToken currentToken) {

            }
        };

        profileTracker = new ProfileTracker() {
            @Override
            protected void onCurrentProfileChanged(Profile oldProfile, Profile currentProfile) {
                goToHomePage(currentProfile);
            }
        };

        accessTokenTracker.startTracking();
        profileTracker.startTracking();
    }


    private void init() {
        loginButton = findViewById(R.id.login_button);
    }

    private void goToHomePage(Profile profile) {
        if (profile != null){
            Intent homeIntent = new Intent(LoginActivity.this, HomeActivity.class);
            bundle = new Bundle();
            bundle.putString(ActivityUtils.USER_NAME, profile.getName());
            bundle.putString(ActivityUtils.USER_AVATAR, profile.getProfilePictureUri(75,75).toString());
            homeIntent.putExtra(ActivityUtils.HOME_KEY_PUT_EXTRA, bundle);
            startActivity(homeIntent);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Profile profile = Profile.getCurrentProfile();
        goToHomePage(profile);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        accessTokenTracker.stopTracking();
        profileTracker.stopTracking();
    }
}
