package com.wangsy.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.*;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnClickListener {

    private Button btn_next;
    private Button btn_photo;
    private ImageView image_photo;
    private Button btn_adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_next =(Button) findViewById(R.id.btn_next);
        btn_photo = (Button)findViewById(R.id.btn_photo);
        btn_adapter = (Button)findViewById(R.id.btn_adapter);

        btn_next.setOnClickListener(this);
        btn_photo.setOnClickListener(this);
        btn_adapter.setOnClickListener(this);
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
        }
        if(view.getId()==R.id.btn_adapter) {
            Intent intent = new Intent();
            intent.setClass(this,MyListView.class);
            startActivity(intent);
        }
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
