package com.rmwebfx.railsjobs;

import com.rmwebfx.railsjobs.model.SearchLocation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class StartScreen extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        Log.d("Rails Jobs Starting:","StartScreen");
        
        initializeLocation();
    }
    
    public void initializeLocation() {
    	SearchLocation location = (SearchLocation) new SearchLocation(this).getLatestRecord();
    	
    	if (location == null || location.getCity() == null || location.getState() == null
    			|| location.getPostal() == null) {
    		// no location set, redirect to Set Location activity
    		Intent intent = new Intent().setClass(this, SetLocation.class);
    		startActivity(intent);
    	} else {
    		// a location is set, so let's show some jobs!
    		Intent intent = new Intent().setClass(this, JobsViewer.class);
    		startActivity(intent);
    	}
    }
}