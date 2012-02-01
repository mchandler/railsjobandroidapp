package com.rmwebfx.railsjobs;

import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class RailsJobsActivity extends Activity {
	
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
	        // TODO:
	        return true;
	    case R.id.menu_jobs:
	        // TODO: 
	        return true;
	    case R.id.menu_about:
	        // TODO: 
	        return true;
	    default:
	        return super.onOptionsItemSelected(item);
	    }
	}
}