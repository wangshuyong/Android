package com.wangsy.com.wangsy.util;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yangsy on 2016/7/21.
 */
public class SharedUtil {
    private Context mContext;

    public SharedUtil() {

    }
    public SharedUtil(Context mContext) {
        this.mContext=mContext;
    }

    public void saveSP (String username,String password){
        SharedPreferences sp = mContext.getSharedPreferences("userinfoSP",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("username",username);
        editor.putString("password",password);
        editor.commit();
    }
    public Map<String,String> readSP () {
        Map<String, String> data = new HashMap<String, String>();
        SharedPreferences sp = mContext.getSharedPreferences("userinfoSP", Context.MODE_PRIVATE);
        data.put("username", sp.getString("username", ""));
        data.put("password", sp.getString("password", ""));
        return data;
    }
}
