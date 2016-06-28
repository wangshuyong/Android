package com.sea.hellomm;

import com.sea.hellomm.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;

public class Activity_Tack extends Activity{

	private Button btn;
	private TextView textView_phone;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tack);
		textView_phone = (TextView) findViewById(R.id.textView_tack);
//		String view_text = "click";
		SpannableString spannableString = new SpannableString(textView_phone.getText());
		spannableString.setSpan(new ClickableSpan() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.putExtra("phone_num", textView_phone.getText().toString());
				setResult(0, intent);
				finish();
			}
		}, 0, textView_phone.getText().length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		textView_phone.setText(spannableString);
		textView_phone.setMovementMethod(LinkMovementMethod.getInstance());
		/**
		textView_phone.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.putExtra("phone_num", textView_phone.getText());
				setResult(0, intent);
				finish();
			}
		});
		**/
	}
	
}
