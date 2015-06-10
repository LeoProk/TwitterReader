package com.example.leonid.twitterreader.Utilities;


import com.example.leonid.twitterreader.Interfaces.FactoryInterface;

import android.app.Fragment;
import android.content.Context;

/**
 * Factory for network statues , retrieving files , saving and appending new files.
 */
public class UtilitiesFactory {

    //Check for network status return bool
    public static FactoryInterface checkNetwork(Context context) {
        return new NetworkCheck(context);
    }

    //Gets file from external storage by name
    public static FactoryInterface getFile(Context context, String filename) {
        return new FileGetter(context, filename);
    }

    //Save file to external storage with name and message
    public static FactoryInterface saveFile(Context context, String filename, String message) {
        return new SaveFile(context, filename, message);
    }

    //Appends message to existing file
    public static FactoryInterface appendFile(Context context, String filename, String message) {
        return new AppendFile(context, filename, message);
    }

    //Adds new fragment.
    public static FactoryInterface addFragment(Context context, Fragment fragment, String tag,
            boolean addToBackStack) {
        return new AddFragment(context, fragment, tag, addToBackStack);
    }

    //Replace the current fragment with new fragment.
    public static FactoryInterface replaceFragment(Context context, Fragment fragment, String tag,
            boolean addToBackStack) {
        return new ReplaceFragment(context, fragment, tag, addToBackStack);
    }
}