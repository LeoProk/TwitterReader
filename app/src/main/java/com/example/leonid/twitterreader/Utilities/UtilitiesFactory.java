package com.example.leonid.twitterreader.Utilities;


import com.example.leonid.twitterreader.Interfaces.FactoryInterface;

import android.content.Context;

/**
 * factory that contain get file from internal storage and check for connection
 */
public class UtilitiesFactory {

    public static FactoryInterface checkNetwork(Context context) {
        return new NetworkCheck(context);
    }

    public static FactoryInterface getFile(Context context, String filename) {
        return new FileGetter(context, filename);
    }
}
