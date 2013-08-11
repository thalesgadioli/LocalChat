package com.thalesgadioli.main;

import java.lang.ref.WeakReference;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

//import com.thalesgadioli.bd.Message;


public class SplashActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);

		IncomingHandler mHandler = new IncomingHandler(this);
		mHandler.sendEmptyMessageDelayed(Activity.RESULT_OK, 2000);
	}
	
	public void handleMessage(Message msg) {
		startActivity(new Intent(this, MainActivity.class));
		finish();
	}

	protected static class IncomingHandler extends Handler {

		private final WeakReference<SplashActivity> mTarget;

		public IncomingHandler(SplashActivity target) {
			mTarget = new WeakReference<SplashActivity>(target);
		}

		@Override
		public void handleMessage(Message msg) {
			SplashActivity fragment = mTarget.get();

			if (fragment != null) {
				fragment.handleMessage(msg);
			}
		}

	}

}