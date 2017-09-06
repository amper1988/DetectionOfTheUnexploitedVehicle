package com.belspec.app.utils;

import android.content.Context;

public class AppHolder {
    private Context mContext;
    private static AppHolder instance;

    private AppHolder(){
    }

    public static AppHolder getInstance(){
        if(instance == null)
            instance = new AppHolder();
        return instance;
    }

    public void setmContext(Context context){
        this.mContext = context;
    }

    public Context getContext(){
        return mContext;
    }
}
