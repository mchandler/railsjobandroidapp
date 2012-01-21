package com.rmwebfx.railsjobs;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.rmwebfx.common.json.IServerRequestor;
import com.rmwebfx.common.json.ServerCaller;
import com.rmwebfx.railsjobs.helpers.JobsArrayBuilder;
import com.rmwebfx.railsjobs.helpers.LocationStringFormatter;
import com.rmwebfx.railsjobs.model.Job;
import com.rmwebfx.railsjobs.model.SearchLocation;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class JobsViewer extends Activity implements IServerRequestor{
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        // On the layout, let's display a "processing" image here
        Log.d("Rails Jobs Finder:","Looking for jobs...");
        
        SearchLocation location = (SearchLocation) new SearchLocation(this).getLatestRecord();
        // **************************************************** //
        // TODO: Remove this test data
        location.setCity("Simi Valley");
        location.setState("CA");
        location.setPostal("93065");
        // TODO: Remove test data above
        // **************************************************** //
    		
        URL feedURL = null;
		try {
			feedURL = new URL(LocationStringFormatter.doFormat(location));
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ServerCaller caller = new ServerCaller(this, feedURL);
		Thread jsonFinder = new Thread(caller);
		jsonFinder.start(); // spawn a thread that calls the feed.
    }

	public void handleServerResponse(JSONObject json) throws JSONException {
		Log.d("JSON", json.toString());
		JSONArray resultsArray = json.getJSONArray("results");
		JobsArrayBuilder builder = new JobsArrayBuilder();
		ArrayList<Job> jobsArray = (ArrayList<Job>) builder.parse(resultsArray);
		Log.d("ArrayDEBUG", new Integer(jobsArray.size()).toString());
	}
    
}