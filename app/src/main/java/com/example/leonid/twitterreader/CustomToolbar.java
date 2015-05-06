package com.example.leonid.twitterreader;

import android.content.Context;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;

/**
 * This class contain logic of drawer and toolbar
 */
public class CustomToolbar {
    Context context;
    public CustomToolbar(Context context){this.context = context;}

    //replace the default action bat with toolbar
    public void menuActionBarHandling(android.support.v7.widget.Toolbar toolbar){
        final ActionBarActivity activity = (ActionBarActivity) context;
        activity.setSupportActionBar(toolbar);
        ActionBar ab = activity.getSupportActionBar();
        ab.setDisplayShowHomeEnabled(true);
        ab.setIcon(R.drawable.main_icon);
        ab.setDisplayUseLogoEnabled(true);
        activity.getSupportActionBar().setDisplayShowTitleEnabled(false);
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        activity.getSupportActionBar().setHomeButtonEnabled(true);

    }
}
