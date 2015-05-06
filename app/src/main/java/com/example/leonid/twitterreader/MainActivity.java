package com.example.leonid.twitterreader;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.ListFragment;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.example.leonid.twitterreader.Drawer.CustomDrawer;
import com.example.leonid.twitterreader.Fragments.SearchTweetsFragment;
import com.example.leonid.twitterreader.Twitter.TwitterLogin;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;


public class MainActivity extends ActionBarActivity {
    ActionBarDrawerToggle mDrawerToggle;
    private TwitterLoginButton loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //call the class for login
        TwitterLogin twitterLogin = new TwitterLogin(this);
       /* //crate the twitter login if network avaliable
        loginButton=(TwitterLoginButton) findViewById(R.id.twitter_login_button);
        if(isNetworkConnected()){
        twitterApi.twitterLogIn(loginButton);}*/
        //create the toolbar
        CustomToolbar customToolbar = new  CustomToolbar(this);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        customToolbar.menuActionBarHandling(toolbar);
        //create the drawer
        DrawerLayout mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ListView mDrawerList = (ListView) findViewById(R.id.slider_list);
        CustomDrawer customDrawer = new CustomDrawer(this,mDrawerLayout,mDrawerList);
        mDrawerToggle = customDrawer.crateDrawer();
        //create the twitter time line fragment

        Fragment fragment = new SearchTweetsFragment();
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().add(R.id.frame_container, fragment, "twitter").addToBackStack("twitter").commit();
    }


    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Pass the event to ActionBarDrawerToggle, if it returns
        // true, then it has handled the app icon touch event
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Pass the activity result to the fragment, which will
        // then pass the result to the login button.
        Fragment fragment = getFragmentManager()
                .findFragmentByTag("twitter");
        if (fragment != null) {
            fragment.onActivityResult(requestCode, resultCode, data);
        }
    }

    // Checks if network connection avaliable  and if not show toast message
    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();
        if (ni == null) {
            Toast.makeText(this, getResources().getString(R.string.no_connection),
                    Toast.LENGTH_LONG).show();
            return false;
        } else
            return true;

    }
}
