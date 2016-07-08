package com.wangsy.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.*;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity implements OnClickListener{

    private Button btn_next;
    private Button btn_photo;
    private ImageView image_photo;
    private Button btn_adapter;
    private AlertDialog.Builder builder;
    private Handler mHandle,workHandler;
    private static final int START=0;
    private static final int FINISH=100;
    private Button btn_download;
    private ProgressBar pb_progress;
    private TextView tv_progress;
    private Button btn_users;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_next =(Button) findViewById(R.id.btn_next);
        btn_photo = (Button)findViewById(R.id.btn_photo);
        btn_adapter = (Button)findViewById(R.id.btn_adapter);
        btn_download = (Button) findViewById(R.id.btn_donwnload);
        tv_progress = (TextView) findViewById(R.id.tv_progress);
        pb_progress = (ProgressBar) findViewById(R.id.pb_progress);
        btn_users = (Button) findViewById(R.id.btn_users);
        initHandler();
        initWorkHandler();
        btn_next.setOnClickListener(this);
        btn_photo.setOnClickListener(this);
        btn_adapter.setOnClickListener(this);
        btn_download.setOnClickListener(this);
        btn_users.setOnClickListener(this);
    }

    private void initWorkHandler() {

        new Thread(){
            @Override
            public void run() {
                Looper.prepare();
                workHandler = new Handler(){
                    @Override
                    public void handleMessage(Message msg) {
                        if(msg.what==START) {
                            for (int i=1;i<=100;i++){
                                try {
                                    sleep(5);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                pb_progress.setProgress(i);
                                Message msg2 = Message.obtain();
                                msg2.arg1=i;
                                msg2.what=START;
                                mHandle.sendMessage(msg2);
                            }
                            Message msg1 =new Message();
                            msg1.what=FINISH;
                            mHandle.sendMessage(msg1);
                        }
                    }
                };
                Looper.loop();

            };
        }.start();
    }

    private void initHandler() {
        mHandle = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case START:
                        tv_progress.setText(msg.arg1+"%");
                        break;
                    case FINISH:
                        Toast.makeText(MainActivity.this,"下载完成",Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        };
    }
    @Override
    public void onClick(View view) {
        if (view.getId()==R.id.btn_next) {
            Intent intent = new Intent();
            intent.setClass(this,TestActivity.class);
            startActivity(intent);
        }
        if(view.getId()==R.id.btn_photo){
//            image_photo.setImageBitmap();
            builder = new AlertDialog.Builder(this);
            builder.setTitle("TEST").setMessage("Are you sure?").setPositiveButton("ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    showDialog("Ok");
                }
            }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    showDialog("cancel");
                }
            });
            builder.create().show();
        }
        if(view.getId()==R.id.btn_adapter) {
            Intent intent = new Intent();
            intent.setClass(this,MyListView.class);
            startActivity(intent);
        }
        if(view.getId()==R.id.btn_users) {
            Intent intent = new Intent();
            intent.setClass(this,UserListActivity.class);
            startActivity(intent);
        }

        if (view.getId()==R.id.btn_donwnload) {
            workHandler.sendEmptyMessage(START);

        }
    }
    private void showDialog(String str) {
        new AlertDialog.Builder(MainActivity.this).setMessage(str).show();
    }
    public void getPhoto () {
        try {
            URL url = new URL("");
            HttpURLConnection photoURL= (HttpURLConnection) url.openConnection();
            photoURL.setRequestMethod("GET");
            photoURL.setConnectTimeout(5000);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
