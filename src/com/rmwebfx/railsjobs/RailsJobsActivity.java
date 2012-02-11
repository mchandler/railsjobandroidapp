package com.rmwebfx.railsjobs;

import com.google.ads.AdRequest;
import com.google.ads.AdSize;
import com.google.ads.AdView;
import com.rmwebfx.common.dialog.DialogHelper;
import com.rmwebfx.railsjobs.config.Constants;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.ViewGroup;

public class RailsJobsActivity extends Activity {
	
	private AdView adView;
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.app_menu, menu);
	    return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	    case R.id.menu_location:
	    	Intent location = new Intent().setClass(this, SetLocation.class);
    		startActivity(location);
	        return true;
	    case R.id.menu_jobs:
	    	Intent jobs = new Intent().setClass(this, StartScreen.class);
    		startActivity(jobs); 
	        return true;
	    case R.id.menu_about:
	        AlertDialog alert = DialogHelper.simpleDialog(this, "My about text here", "OK");
	        alert.show();
	        return true;
	    default:
	        return super.onOptionsItemSelected(item);
	    }
	}
	
	public void showAd(int viewId) {
		ViewGroup viewGroup = (ViewGroup) findViewById(viewId);
		adView = new AdView(this, AdSize.BANNER, Constants.googleAdsPublisherId);
		viewGroup.addView(adView);
		
		AdRequest adRequest = new AdRequest();
		adRequest.addTestDevice(AdRequest.TEST_EMULATOR);
		adView.loadAd(adRequest);
	}
	
	@Override
	public void onDestroy() {
		adView.destroy();
		super.onDestroy();
	}
}