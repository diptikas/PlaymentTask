package com.playment.diptika.playmenttask.ui.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.playment.diptika.playmenttask.R;

public class SplashActivity extends AppCompatActivity implements Runnable {

    private static final int SPLASH_TIME_OUT = 3000;
    private Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        mHandler = new Handler();
        mHandler.postDelayed(this, SPLASH_TIME_OUT);
    }

    @Override
    protected void onDestroy() {
        if (mHandler != null) {
            mHandler.removeCallbacks(this);
        }
        super.onDestroy();
    }

    @Override
    public void run() {
        startActivity(new Intent(this, SearchMovieActivity.class));
        finish();
    }


}
