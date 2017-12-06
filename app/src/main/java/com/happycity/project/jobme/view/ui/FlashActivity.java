package com.happycity.project.jobme.view.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.happycity.project.jobme.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FlashActivity extends AppCompatActivity implements Animation.AnimationListener{

    @BindView(R.id.layoutSplash)
    RelativeLayout layoutSplash;
    @BindView(R.id.imgSplashLogo)
    ImageView imageSplashLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash);
        ButterKnife.bind(this);
        initAnimation();
    }

    private void initAnimation() {
        Animation transitionAnim = AnimationUtils.loadAnimation(this, R.anim.transaction_icon);
        imageSplashLogo.setAnimation(transitionAnim);
        Animation alphaAnim = AnimationUtils.loadAnimation(this, R.anim.alpha_background);
        layoutSplash.setAnimation(alphaAnim);
        alphaAnim.setAnimationListener(this);
    }

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(FlashActivity.this, LoginActivity.class));
                finish();
            }
        }, 2000);
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
}
