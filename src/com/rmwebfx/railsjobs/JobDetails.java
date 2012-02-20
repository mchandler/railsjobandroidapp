package com.rmwebfx.railsjobs;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class JobDetails extends MenuAndAdActivity {
    
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jobdetails);
        
        Bundle extras = getIntent().getExtras();
        String indeedUrl = extras.getString("indeedUrl");
        
        WebView jobWindow = (WebView) findViewById(R.id.indeedView);
        jobWindow.setWebViewClient(new IndeedWebViewClient()); // see nested class below
        
        showAd(R.id.jobDetailsAd);
        jobWindow.loadUrl(indeedUrl);
    }
	
	/**
	 * 
	 * This class allows me to override WebView behavior and prevent Android from firing
	 * up the browser when Indeed redirects to their mobile friendly website.
	 *
	 */
	private class IndeedWebViewClient extends WebViewClient {
	    @Override
	    public boolean shouldOverrideUrlLoading(WebView view, String url) {
	        view.loadUrl(url);
	        return true;
	    }
	}
}