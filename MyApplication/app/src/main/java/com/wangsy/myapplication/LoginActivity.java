package com.wangsy.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String HOSTPATH = "http://10.130.62.37:8080/CmsAdmin/web/Login_login";
    private AutoCompleteTextView tv_phone;
    private EditText et_password;
    private Button btn_sign_in;
    private Handler mHandler,workHandler;
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        tv_phone = (AutoCompleteTextView) findViewById(R.id.tv_phone);
        et_password = (EditText) findViewById(R.id.et_password);
        btn_sign_in = (Button) findViewById(R.id.btn_sign_in);
        btn_sign_in.setOnClickListener(this);
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
        mHandler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                if(msg.what==1){
                    Toast.makeText(LoginActivity.this,"连接成功",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent();
                    intent.setClass(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        };
    }

    @Override
    public void onClick(View view) {
        new Thread() {
            @Override
            public void run() {
                String phone = tv_phone.getText().toString();
                String pass = et_password.getText().toString();
                StringBuilder str = new StringBuilder(HOSTPATH);
                str.append("?phone=").append(phone).append("&&password=").append(pass);
                try {
                    URL url = new URL(str.toString());
                    HttpURLConnection httpconn = (HttpURLConnection) url.openConnection();
                    httpconn.setRequestMethod("GET");
                    httpconn.setConnectTimeout(25000);
                    httpconn.setReadTimeout(13000);
                    if (httpconn.getResponseCode() != 200) {
                        Toast.makeText(LoginActivity.this, "连接失败", Toast.LENGTH_SHORT).show();
                        return;
                    } else {
                        JSONObject json = new JSONObject();

                        String result = httpconn.getResponseMessage();
                        Message msg = Message.obtain();
                        msg.what=1;
                        msg.obj=result;
                        mHandler.sendMessage(msg);
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();

    }

    @Override
    public void onStart() {
        super.onStart();
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW,
                "Login Page",
                Uri.parse("http://host/path"),
                Uri.parse("android-app://com.wangsy.myapplication/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Login Page", // TODO: Define a title for the content shown.
                Uri.parse("http://host/path"),
                Uri.parse("android-app://com.wangsy.myapplication/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}
