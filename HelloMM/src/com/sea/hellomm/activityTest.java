package com.sea.hellomm;

import com.sea.hellomm.R;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.*;

public class activityTest extends Activity{

	private Button btn;
	private TextView textView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_test);
		Bundle bundle = getIntent().getExtras();
		String data = bundle.getString("data");
		btn = (Button)findViewById(R.id.new_button1);
		textView = (TextView) findViewById(R.id.textView1);
		textView.append(data);
		Log.d("接收的数据时：", data);
	}
	
}
