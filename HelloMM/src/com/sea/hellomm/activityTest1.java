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
		textView.append("��"+user.getUserName()+" ,��������ǣ�  "+user.getPassWord());
		textView.append("======�ڶ��ַ������ݵ���ֵ==========="+user.getUserName()+" ,��������ǣ�  "+user.getPassWord());
		Log.d("��¼��", user.getUserName());
	}
	
}