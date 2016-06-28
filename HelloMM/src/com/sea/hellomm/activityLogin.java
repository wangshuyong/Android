package com.sea.hellomm;

import com.sea.hellomm.R;
import com.sea.model.User;
import com.sea.model.User2;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class activityLogin extends Activity{

	private TextView textView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		Bundle bundle = getIntent().getExtras();
//		User user = (User) bundle.get("login");
		User2 user2 = (User2) bundle.get("login2");
		textView = (TextView) findViewById(R.id.textView1);
		textView.append("£¬"+user2.getUserName()+" ,ÄãµÄÃÜÂëÊÇ£º  "+user2.getPassWord());
		Log.d("µÇÂ¼Ãû", user2.getUserName());
	}
	
}
