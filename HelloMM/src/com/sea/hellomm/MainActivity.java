package com.sea.hellomm;

import com.sea.hellomm.R;
import com.sea.model.User;
import com.sea.model.User2;
import com.sea.util.ApplicationUtil;

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

	private CharSequence button3 = "µÇÂ¼";
	private CharSequence button4 = "button4";
	private Button myButton1;
	private Button myButton2;
	private Button myButton3;
	private Button myButton4;
	private Button btn_tack;
	private Button btn_tack1;
	private EditText phoneEditText;
	private EditText edit_Text_phone_num;
	private EditText userNameEditText;
	private EditText passWordEditText;
	
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
		btn_tack = (Button) findViewById(R.id.btn_tack);
		btn_tack1 = (Button) findViewById(R.id.btn_tack1);
		phoneEditText = (EditText)findViewById(R.id.editText1);
		edit_Text_phone_num = (EditText)findViewById(R.id.phone_num);
		userNameEditText = (EditText)findViewById(R.id.username);
		passWordEditText = (EditText)findViewById(R.id.password);
		myButton4.setText(button4);
		myButton3.setText(button3);
		myButton1.setOnClickListener(this);
		myButton2.setOnClickListener(this);
		myButton3.setOnClickListener(this);
		myButton4.setOnClickListener(this);
		btn_tack.setOnClickListener(this);
		btn_tack1.setOnClickListener(this);
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
			intent2.putExtra("data", phoneNum);
			startActivity(intent2);
			break;
		case R.id.my_button3 :
			Intent intent3 = new Intent();
			intent3.setClassName(this, "com.sea.hellomm.activityLogin");
//			User user = new User();
//			user.setUserName(userNameEditText.getText().toString());
//			user.setPassWord(passWordEditText.getText().toString());
//			intent3.putExtra("login", user);
			
			User2 user2 = new User2();
			user2.setUserName(userNameEditText.getText().toString());
			user2.setPassWord(passWordEditText.getText().toString());
			intent3.putExtra("login2", user2);
			startActivity(intent3);
			break;
		case R.id.my_button4 :
			Intent intent4 = new Intent();
			intent4.setAction("com.sea.intent.TestActionmm");
			intent4.addCategory(Intent.CATEGORY_DEFAULT);
			intent4.setData(Uri.parse("http://www.baidu.com"));
			User user1 = new User();
			user1.setUserName(userNameEditText.getText().toString());
			user1.setPassWord(passWordEditText.getText().toString());
			ApplicationUtil app =ApplicationUtil.getInstance();
			app.getMap().put("user",user1);
//			intent4.putExtra(name, value)("login4", app);
			startActivity(intent4);
			break;
			
		case R.id.btn_tack :
			Intent intent5 = new Intent();
			intent5.setClassName(this, "com.sea.hellomm.Activity_Tack");
			startActivityForResult(intent5, 0);
			break;
		case R.id.btn_tack1 :
			Intent intent51 = new Intent();
			intent51.setClassName(this, "com.sea.hellomm.Activity_Tack");
//			intent5.putExtra("data", phoneNum);
			startActivityForResult(intent51, 1);
			break;
		}
		
	}
	

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		Bundle bundle = data.getExtras();
		String phone_num = bundle.getString("phone_num");
		if(requestCode==0) {
			phoneEditText.setText(phone_num);
		} else if (requestCode==1) {
			edit_Text_phone_num.setText(phone_num);
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);
	}
}