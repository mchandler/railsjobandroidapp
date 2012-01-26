package com.rmwebfx.railsjobs;

import com.rmwebfx.railsjobs.model.SearchLocation;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class SetLocation extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setlocation);
        
        Log.d("Rails Jobs Starting:","SetLocation");
    }
    
}