package com.wangsy.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class UserListActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    private ListView listview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview);
        listview = (ListView) findViewById(R.id.listview);
        ArrayList<HashMap<String,Object>> listItem = new ArrayList<HashMap<String,Object>>();/*在数组中存放数据*/
        for(int i=0;i<10;i++)
        {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("id", i);
            map.put("phone",1310000000+i);
            map.put("realname", "user"+i);
            map.put("email", "wang"+i+"@163.com");
            listItem.add(map);
        }
        SimpleAdapter adapter = new SimpleAdapter(this,listItem,R.layout.activity_user_list,new String[]{"id","phone","realname","email"},new int[]{R.id.user_id,R.id.tv_phone,R.id.real_name,R.id.email});
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        setTitle("you click "+i+"line");
    }
}
