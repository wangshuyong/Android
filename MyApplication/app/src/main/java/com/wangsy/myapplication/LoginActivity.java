package com.wangsy.myapplication;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

//    private static final String HOSTPATH = "http://10.130.18.205:8080/CmsAdmin/web/Login_login";
    private static final String HOSTPATH = "http://10.130.62.51:8080/CmsAdmin/web/Login_login";
    private final static String TAG = "LoginActivity";
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
//                    String result = (String) msg.obj;
//                    jsonParseStr(result);
                    try {
                        JSONObject jsonObject = new JSONObject(msg.obj.toString());
                        String name = (String) jsonObject.get("message");
                        FileOutputStream fos =openFileOutput("login.txt", Context.MODE_PRIVATE);
                        fos.write(name.getBytes());
                        fos.close();
                        Toast.makeText(LoginActivity.this,name,Toast.LENGTH_LONG).show();
                        Intent intent = new Intent();
                        intent.setClass(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

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
                    int num = httpconn.getResponseCode();
                    Log.d("num",num+"");
                    if (httpconn.getResponseCode() != 200) {
                        Toast.makeText(LoginActivity.this, "连接失败", Toast.LENGTH_SHORT).show();
                        return;
                    } else {
                        InputStreamReader isr = new InputStreamReader(httpconn.getInputStream());
                        BufferedReader br = new BufferedReader(isr);
                        String result = "";
                        StringBuilder builder = new StringBuilder();
                        for (String s = br.readLine(); s != null; s = br.readLine()) {
                            builder.append(s);
                    }
                        isr.close();
                        httpconn.disconnect();

                        Message msg = Message.obtain();
                        msg.what=1;
                        msg.obj=builder.toString();
                        mHandler.sendMessage(msg);

                    }
                } catch (MalformedURLException e) {
                    Toast.makeText(LoginActivity.this, "连接服务器失败", Toast.LENGTH_SHORT).show();
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

    private void jsonParseStr(String response) {
        if(response!=null&&response.startsWith("\ufeff")){
            response = response.substring(1);
            try {
                JSONObject jsonObject = new JSONObject(response);
                String name = (String) jsonObject.get("message");
                Log.i(TAG, "message: " + name);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
