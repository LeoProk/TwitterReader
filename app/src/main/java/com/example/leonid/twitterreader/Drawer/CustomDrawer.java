package com.example.leonid.twitterreader.Drawer;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.res.TypedArray;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.leonid.twitterreader.Fragments.LoginFragment;
import com.example.leonid.twitterreader.Fragments.SearchTweetsFragment;
import com.example.leonid.twitterreader.R;

import java.util.ArrayList;

/**
 * This class contain logic of drawer and toolbar
 */
public class CustomDrawer {
    Context context;
    DrawerLayout mDrawerLayout;
    ListView mDrawerList;
    public CustomDrawer(Context context, DrawerLayout mDrawerLayout, ListView mDrawerList){
        this.context = context;
        this.mDrawerLayout = mDrawerLayout;
        this.mDrawerList = mDrawerList;
    }

    //class of creating and populating navigation drawer
    public ActionBarDrawerToggle crateDrawer() {
        final ActionBarActivity activity = (ActionBarActivity) context;
        //get the arrays from strings
        final String[] menutitles = context.getResources().getStringArray(R.array.titles);
        final TypedArray menuIcons = context.getResources().obtainTypedArray(R.array.icons);
        ArrayList<RowItem> rowItems = new ArrayList<>();
        //create the drawer
        ActionBarDrawerToggle  mDrawerToggle = new ActionBarDrawerToggle(activity, mDrawerLayout, R.string.drawer_open, R.string.drawer_close) {

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                activity.invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                activity.invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        for (int i = 0; i < menutitles.length; i++) {
            RowItem items = new RowItem(menutitles[i], menuIcons.getResourceId(i, -1));
            rowItems.add(items);

        }
        menuIcons.recycle();
        CustomAdapter adapter = new CustomAdapter(context, rowItems);
        mDrawerList.setAdapter(adapter);
        mDrawerList.setOnItemClickListener(new SlideitemListener());

        return  mDrawerToggle;
    }
    // called when one of the items in drawer is clicked
    class SlideitemListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView parent, View view, int position, long id) {
           updateDisplay(position);
        }
    }
    //create fragment based on clicked position
    private void updateDisplay(int position) {
        //check for active fragment and delete them
        final ActionBarActivity activity = (ActionBarActivity) context;
        FragmentTransaction ft = activity.getFragmentManager().beginTransaction();
        if(activity.getFragmentManager().findFragmentByTag("twitter") != null){
            ft.remove(activity.getFragmentManager().findFragmentByTag("twitter")).commit();
        }
        if(activity.getFragmentManager().findFragmentByTag("twitter") != null){
            ft.remove(activity.getFragmentManager().findFragmentByTag("twitter"));
        }
        //create the fragment
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new SearchTweetsFragment();
                break;
            case 1:
                fragment = new LoginFragment();
                break;
            default:
                break;
        }
        if (fragment != null&&isNetworkConnected(context)) {
            FragmentManager fragmentManager = activity.getFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.frame_container, fragment, "twitter").addToBackStack("twitter").commit();
            //closes drawer
            mDrawerLayout.closeDrawer(mDrawerList);

        }
    }

    // Checks if network connection avaliable  and if not show toast message
    private boolean isNetworkConnected(Context context) {
        final ActionBarActivity activity = (ActionBarActivity) context;
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();
        if (ni == null) {
            Toast.makeText(context, activity.getResources().getString(R.string.no_connection),
                    Toast.LENGTH_LONG).show();
            return false;
        } else
            return true;

    }
}
