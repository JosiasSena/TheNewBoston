package com.TheNewBoston;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.google.analytics.tracking.android.EasyTracker;

public class Splash extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);

		Thread timer = new Thread(){
			@Override
			public void run(){
				try {
					sleep(2000); //The time it runs for in milliseconds. Example: 3000ms = 3s
				} catch (InterruptedException e) {
					e.printStackTrace();
				}finally{
					Intent mainActivityIntent = new Intent(Splash.this, MainActivity.class);
					startActivity(mainActivityIntent);
				}
			}
		};
		timer.start();
	}

	  @Override
	  public void onStart() {
	    super.onStart();
	    EasyTracker.getInstance(this).activityStart(this);  // Add this method.
	  }
	
	  @Override
	protected void onStop() {
		  super.onStop();
		  EasyTracker.getInstance(this).activityStop(this);
		  super.finish();
	}
	
}
