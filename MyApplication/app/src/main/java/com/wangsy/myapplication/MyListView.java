package com.wangsy.myapplication;

import android.app.Activity;
import android.app.ListActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyListView extends AppCompatActivity {

    private ListView listView;
    //private List<String> data = new ArrayList<String>();
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview);
        listView = (ListView) findViewById(R.id.listview);
        SimpleAdapter adapter = new SimpleAdapter(this,getData(),R.layout.activity_my_list_view,
                new String[]{"img","title","info"},
                new int[] { R.id.img, R.id.title, R.id.info });
        listView.setAdapter(adapter);
    }

    private List<Map<String, Object>> getData(){
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("img", R.drawable.biaoqing);
        map.put("title", "小宗");
        map.put("info", "电台DJ");
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("img", R.drawable.biaoqing);
        map.put("title", "貂蝉");
        map.put("info", "四大美女");
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("img", R.drawable.biaoqing);
        map.put("title", "奶茶");
        map.put("info", "清纯妹妹");
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("img", R.drawable.biaoqing);
        map.put("title", "大黄");
        map.put("info", "是小狗");
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("img", R.drawable.biaoqing);
        map.put("title", "hello");
        map.put("info", "every thing");
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("img", R.drawable.biaoqing);
        map.put("title", "world");
        map.put("info", "hello world");
        list.add(map);

        return list;
    }
}
