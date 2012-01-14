package com.rmwebfx.railsjobs;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import org.json.JSONException;
import org.json.JSONObject;

import com.rmwebfx.common.json.IServerRequestor;
import com.rmwebfx.common.json.ServerCaller;
import com.rmwebfx.railsjobs.config.Constants;
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
			feedURL = new URL(constructURL(location));
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ServerCaller caller = new ServerCaller(this, feedURL);
		Thread jsonFinder = new Thread(caller);
		jsonFinder.start(); // spawn a thread that calls the feed.
    }

	public void handleServerResponse(JSONObject json) throws JSONException {
		// TODO Auto-generated method stub
		
	}
	
	public String constructURL(SearchLocation location) {
		StringBuilder sb = new StringBuilder();
		sb.append(Constants.indeedURL + "?v=2&publisher=" + Constants.publisherID);
		sb.append("&q=" + Constants.searchQuery);
		
		sb.append("&l=" + doLocationString(location));
		
		sb.append(doLimitString());
		sb.append("&format=json");
		
		Log.d("Remote URL",sb.toString());
		
		return sb.toString();
	}
    
    public String doLocationString(SearchLocation location) {
		StringBuilder sb = new StringBuilder();
		
		if (location.getCity() != null && location.getCity().length() > 0) {
			sb.append(location.getCity().toLowerCase().trim());
		}
		
		if (location.getState() != null && location.getState().length() > 0) {
			if (sb.toString().length() > 0) {
				sb.append(", ");
			}
			sb.append(location.getState().toLowerCase().trim());
		}
		
		if (location.getPostal() != null && location.getPostal().length() > 0) {
			if (sb.toString().length() > 0) {
				sb.append(" ");
			}
			sb.append(location.getPostal().trim());
		}
		
		return URLEncoder.encode(sb.toString());
	}
	
	public String doLimitString() {
		return "&limit=" + Constants.jobCountLimit;
	}
    
}