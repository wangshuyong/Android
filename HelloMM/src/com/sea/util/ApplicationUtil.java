package com.sea.util;

import java.util.HashMap;

import android.app.Application;

public class ApplicationUtil extends Application{

	private static ApplicationUtil instance;
	private HashMap<String,Object> map = new HashMap<String,Object>();
	
	public static ApplicationUtil getInstance() {
		return instance;
	};
	
	@Override
	public void onCreate() {
		super.onCreate();
		this.instance = this;
	}

	public HashMap<String,Object> getMap() {
		return map;
	}

	public void setMap(HashMap<String,Object> map) {
		this.map = map;
	}
	
}
