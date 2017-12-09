package com.happycity.project.jobme;
import android.content.Intent;

import com.happycity.project.jobme.view.ui.HomeActivity;
import com.happycity.project.jobme.view.ui.LoginActivity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.shadows.ShadowApplication;

import static org.junit.Assert.assertEquals;

/**
 * Created by Ha Truong on 12/8/2017.
 * This is a jobme
 * into the com.happycity.project.jobme
 */
@RunWith(RobolectricTestRunner.class)
public class LoginActivityTest {

    LoginActivity activity;

    @Before
    public void setup(){
        // initial activity login
        activity = Robolectric.buildActivity(LoginActivity.class).create().get();
    }



    @Test
    public void clickLoginFacebookShouldStartToHomeActivity(){
        activity = Robolectric.setupActivity(LoginActivity.class);
        activity.findViewById(R.id.login_button);

        Intent exceptedIntent = new Intent(activity, HomeActivity.class);
        Intent actual = ShadowApplication.getInstance().getNextStartedActivity();
        assertEquals(exceptedIntent.getComponent(), actual.getComponent());


    }
}
