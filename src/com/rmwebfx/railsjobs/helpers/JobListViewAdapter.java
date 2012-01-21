package com.rmwebfx.railsjobs.helpers;

import java.util.ArrayList;

import com.rmwebfx.railsjobs.model.Job;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class JobListViewAdapter extends BaseAdapter {

	private ArrayList<Job> jobArray;
	private LayoutInflater inflater;

	public JobListViewAdapter(Context context, ArrayList<Job> logArray) {
		this.jobArray = jobArray;
		inflater = LayoutInflater.from(context);
	}

	public int getCount() {
		return jobArray.size();
	}

	public Object getItem(int position) {
		return jobArray.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		return null;
	}
}