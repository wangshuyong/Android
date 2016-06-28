package com.sea.hellomm;

import com.sea.hellomm.R;
import com.sea.model.User;
import com.sea.model.User2;
import com.sea.util.ApplicationUtil;

import android.app.Activity;
import android.os.BaseBundle;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class activityTest1 extends Activity{

	private TextView textView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_test1);
		
		ApplicationUtil appUtil = ApplicationUtil.getInstance();
		User user = (User) appUtil.getMap().get("user");
		
		textView = (TextView) findViewById(R.id.textView1);
		textView.append("，"+user.getUserName()+" ,你的密码是：  "+user.getPassWord());
		textView.append("======第二种方法传递的数值==========="+user.getUserName()+" ,你的密码是：  "+user.getPassWord());
		Log.d("登录名", user.getUserName());
	}
	
}