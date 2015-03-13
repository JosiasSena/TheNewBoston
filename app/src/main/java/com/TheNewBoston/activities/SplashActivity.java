package com.TheNewBoston.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.TheNewBoston.R;
import com.google.analytics.tracking.android.EasyTracker;

public class SplashActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);

        Handler mHandler = new Handler();
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent mainActivityIntent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(mainActivityIntent);
            }
        }, 500);
	}

	  @Override
	  public void onStart() {
	    super.onStart();
	    EasyTracker.getInstance(this).activityStart(this);
	  }
	
	  @Override
	protected void onStop() {
		  super.onStop();
		  EasyTracker.getInstance(this).activityStop(this);
		  super.finish();
	}
	
}
