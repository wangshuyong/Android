package com.sea.hello;

import com.sea.hello.R;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class MainActivity extends Activity implements OnClickListener{

	private CharSequence text = "new Hello!";
	private CharSequence text1 = "intent!";
	private Button myButton1;
	private Button myButton2;
	private Button myButton3;
	private Button myButton4;
	private EditText phoneEditText;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
//		ImageView imagekoala = new ImageView(this);
//		imagekoala.setImageResource(R.drawable.koala);
//		setContentView(imagekoala);
		setTheme(android.R.style.Theme_Black);
		myButton1 = (Button) findViewById(R.id.my_button1);
		myButton2 = (Button) findViewById(R.id.my_button2);
		myButton3 = (Button) findViewById(R.id.my_button3);
		myButton4 = (Button) findViewById(R.id.my_button4);
		phoneEditText = (EditText)findViewById(R.id.editText1);
		myButton4.setText(text1);
		myButton3.setText(text);
		myButton1.setOnClickListener(this);
		myButton2.setOnClickListener(this);
		myButton3.setOnClickListener(this);
		myButton4.setOnClickListener(this);
	}
	
	@Override
	public void onClick(View v) {
		String phoneNum = phoneEditText.getText().toString();
		switch(v.getId()) {
		case R.id.my_button1 :
//			Intent intent = new Intent();
//			intent.setClass(this, activityTest.class);
//			startActivity(intent);
			Intent intent=new Intent(Intent.ACTION_CALL,Uri.parse("tel:"+phoneNum));
			startActivity(intent);
			break;
		case R.id.my_button2 :
			Intent intent2 = new Intent();
			intent2.setClassName(this, "com.sea.hellomm.activityTest");
			startActivity(intent2);
			break;
		case R.id.my_button3 :
			Intent intent3 = new Intent();
			intent3.setClassName("com.sea.hello", "com.sea.hellomm.activityTest1");
			startActivity(intent3);
			break;
		case R.id.my_button4 :
			Intent intent4 = new Intent();
			intent4.setAction("com.sea.intent.TestAction");
			intent4.addCategory(Intent.CATEGORY_DEFAULT);
			intent4.setData(Uri.parse("http://www.baidu.com"));
			startActivity(intent4);
			break;
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
