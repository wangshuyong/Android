package com.wangsy.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String HOSTPATH = "http://10.130.18.205:8080/CmsAdmin/web/Login_login";
//    private static final String HOSTPATH = "http://10.130.62.51:8080/CmsAdmin/web/Login_login";
    private final static String TAG = "LoginActivity";
    private AutoCompleteTextView tv_phone;
    private TextView tv_register;
    private EditText et_password;
    private Button btn_sign_in;
    private Handler mHandler,workHandler;
    private GoogleApiClient client;
    private CheckBox remember;
    private SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sp = this.getApplicationContext().getSharedPreferences("userinfoSP", Context.MODE_PRIVATE);
        boolean rememberME = sp.getBoolean("remember",false);
        init();
        if(rememberME){
            tv_phone.setText(sp.getString("username",""));
            et_password.setText(sp.getString("password",""));
            remember.setChecked(true);
        }
        mHandler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                if(msg.what==1){
                    try {
                        Bundle data = msg.getData();
                        JSONObject jsonObject = new JSONObject(data.getString("message"));
                        String message = (String) jsonObject.get("message");
                        String flag = (String) jsonObject.get("flag");
                        if(flag.equals("success")){
                            if(remember.isChecked()){
                                saveSP(data.getString("username"),data.getString("password"),true);
                            }else {
                                saveSP("","",false);
                            }
                            FileOutputStream fos =openFileOutput("login.txt", Context.MODE_PRIVATE);
                            fos.write(message.getBytes());
                            fos.close();
                            Toast.makeText(LoginActivity.this,message,Toast.LENGTH_LONG).show();
                            Intent intent = new Intent();
                            intent.setClass(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(LoginActivity.this,message,Toast.LENGTH_LONG).show();
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else if (msg.what==0){
                    Toast.makeText(LoginActivity.this, "连接服务器失败", Toast.LENGTH_SHORT).show();
                }
                else if(msg.what==2) {
                    Toast.makeText(LoginActivity.this, "请输入正确的用户名密码", Toast.LENGTH_SHORT).show();
                }
            }
        };
    }
    public void init(){
        tv_phone = (AutoCompleteTextView) findViewById(R.id.tv_phone);
        et_password = (EditText) findViewById(R.id.et_password);
        btn_sign_in = (Button) findViewById(R.id.btn_sign_in);
        btn_sign_in.setOnClickListener(this);
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
        remember = (CheckBox) findViewById(R.id.checkbox_remember);
        tv_register = (TextView) findViewById(R.id.tv_register);
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
                Message msg = Message.obtain();
                Bundle data = new Bundle();
                if(phone==""||phone==null||pass==""||pass==null) {
                    msg.what=2;
                    mHandler.sendMessage(msg);
                }else {
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
                            InputStreamReader isr = new InputStreamReader(httpconn.getInputStream());
                            BufferedReader br = new BufferedReader(isr);
                            StringBuilder builder = new StringBuilder();
                            for (String s = br.readLine(); s != null; s = br.readLine()) {
                                builder.append(s);
                            }
                            isr.close();
                            httpconn.disconnect();
                            data.putString("username",phone);
                            data.putString("password",pass);
                            data.putString("message",builder.toString());
                            msg.what=1;
                            msg.setData(data);
                            mHandler.sendMessage(msg);
                        }
                    } catch (MalformedURLException e) {

                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                        msg.what=0;
                        mHandler.sendMessage(msg);
                    }
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
    public void saveSP (String username,String password,boolean remember){
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("username",username);
        editor.putString("password",password);
        editor.putBoolean("remember",remember);
        editor.commit();
    }
}
