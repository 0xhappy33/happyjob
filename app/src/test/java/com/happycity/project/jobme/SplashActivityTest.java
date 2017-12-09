package com.happycity.project.jobme;

import com.happycity.project.jobme.view.ui.FlashActivity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

/**
 * Created by Ha Truong on 12/9/2017.
 * This is a jobme
 * into the com.happycity.project.jobme
 */
@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class SplashActivityTest {

    private FlashActivity flashActivity;

    @Before
    public void setup(){
        flashActivity = Robolectric.buildActivity(FlashActivity.class).create().get();
    }

    @Test
    public void splashTimeOutTest() throws InterruptedException{


    }

}
