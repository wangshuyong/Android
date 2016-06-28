package com.wangsy.myapplication;

import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextWatcher;
import android.text.style.ImageSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.widget.ToggleButton;

public class TestActivity extends AppCompatActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    private Button btn_sendMessage;
    private Button btn_submit;
    private EditText editTX_check;
    private EditText editTX_message;
    private ToggleButton t_button;
    private LinearLayout layout_linear;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        btn_sendMessage = (Button) findViewById(R.id.btn_sendMessage);
        editTX_check = (EditText)findViewById(R.id.editTX_check);
        editTX_message = (EditText)findViewById(R.id.editTX_message);
        btn_submit = (Button)findViewById(R.id.btm_subimit);
        t_button = (ToggleButton)findViewById(R.id.t_button);
        layout_linear = (LinearLayout)findViewById(R.id.layout_login);

        btn_sendMessage.setOnClickListener(this);
        btn_submit.setOnClickListener(this);
        t_button.setOnCheckedChangeListener(this);
        editTX_check.addTextChangedListener(new TextWatcher() {
            private CharSequence temp;
            private int selectionStart;
            private int selectionEnd;
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                temp = s;
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                selectionStart = editTX_check.getSelectionStart();
                selectionEnd = editTX_check.getSelectionEnd();
                Log.d("TAG", "" + selectionStart);
                if (temp.length() > 8) {
                    Toast.makeText(TestActivity.this, "字数不能超过8个",
                            Toast.LENGTH_SHORT).show();
                    s.delete(selectionStart - 1, selectionEnd);
                    int tempSelection = selectionStart;
                    editTX_check.setText(s);
                    editTX_check.setSelection(tempSelection);
                }
                Log.d("TAG", " " + selectionEnd);
            }
        });
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.btn_sendMessage){
            SpannableString sstr = new SpannableString("d");
            ImageSpan is = new ImageSpan(this, BitmapFactory.decodeResource(getResources(),R.drawable.biaoqing));
            sstr.setSpan(is,0,1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            editTX_message.append(sstr);
        }

        if(view.getId()==R.id.btm_subimit) {
            if(editTX_check.getText()==null||editTX_check.length()<=0) {
                editTX_check.setError("该项不能为空!!!!");
            }
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        if (b) {
            layout_linear.setVisibility(View.VISIBLE);
        } else {
            layout_linear.setVisibility(View.GONE);
        }
    }
}
