package com.example.dawid.dietalpha.model;

import android.content.Context;

/**
 * Created by Dawid on 2015-09-06.
 */
public class MyApplication extends android.app.Application {

    private static MyApplication instance;

    public MyApplication() {
        instance = this;
    }

    public static Context getContext() {
        return instance.getApplicationContext();
    }

}

