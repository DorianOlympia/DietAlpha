package com.example.dawid.dietalpha.model;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.Volley;

/**
 * Created by Dawid on 2015-09-06.
 */
public class VolleySingleton {
    private static VolleySingleton sInstance = null;
    private static RequestQueue mRequestQueue;

    private VolleySingleton(){
        // Instantiate the cache
        Cache cache = new DiskBasedCache(MyApplication.getContext().getCacheDir(), 2 * 1024 * 1024); // 1MB cap

// Set up the network to use HttpURLConnection as the HTTP client.
        Network network = new BasicNetwork(new HurlStack());
        mRequestQueue = new RequestQueue(cache, network);
        mRequestQueue.start();
    }

    public static VolleySingleton getInstance(){
        if(sInstance == null)sInstance = new VolleySingleton();
        return sInstance;
    }

    public RequestQueue getRequestQueue(){
        return mRequestQueue;
    }
}
