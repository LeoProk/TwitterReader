package com.example.leonid.twitterreader;

import android.app.Application;
import android.text.TextUtils;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.crashlytics.android.Crashlytics;
import com.example.leonid.twitterreader.VolleyApi.LruBitmapCache;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;

import java.io.FileInputStream;

import io.fabric.sdk.android.Fabric;

/**
 * Created by Leo on 4/28/2015.
 */
public class AppController extends Application {

    private RequestQueue mRequestQueue;
    private ImageLoader mImageLoader;
    private static AppController mInstance;
    public static final String TAG = AppController.class.getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();
    //fabric config
        TwitterAuthConfig authConfig =
                new TwitterAuthConfig("myT2UGT74XR7AIr9VpEaq1HWr",
                        "IPrxSt5x8dSBr18FbykzBHk8ly2Iwfy6HuONblbW2ysDWfDyuH");
        Fabric.with(this, new Twitter(authConfig), new Crashlytics());
        mInstance = this;
    }
    // code needed for volly api
    public static synchronized AppController getInstance() {
        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }

        return mRequestQueue;
    }

    public ImageLoader getImageLoader() {
        getRequestQueue();
        if (mImageLoader == null) {
            mImageLoader = new ImageLoader(this.mRequestQueue,
                    new LruBitmapCache());
        }
        return this.mImageLoader;
    }

    public <T> void addToRequestQueue(Request<T> req, String tag) {
        // set the default tag if tag is empty
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(req);
    }

    public <T> void addToRequestQueue(Request<T> req) {
        req.setTag(TAG);
        getRequestQueue().add(req);
    }

    public void cancelPendingRequests(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }
    public String fileGetter(String file){
        String temp="";
        try{
            FileInputStream inputStream = openFileInput(file);
            int c;

            while( (c = inputStream.read()) != -1){
                temp = temp + Character.toString((char)c);
            }
            //string temp contains all the data of the file.
            inputStream.close();

        }catch(Exception e){

        }
        return temp;
    }
}
