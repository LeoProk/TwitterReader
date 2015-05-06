package com.example.leonid.twitterreader.Fragments;

import android.app.Fragment;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.leonid.twitterreader.R;
import com.example.leonid.twitterreader.Twitter.TwitterLogin;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;

/**
 *
 */
public class LoginFragment extends Fragment {

    private TwitterLoginButton loginButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.login_fragment, container, false);
        final TwitterLogin twitterLogin = new TwitterLogin(getActivity());
        loginButton = (TwitterLoginButton) rootView.findViewById(R.id.twitter_login_button);
        if(isNetworkConnected(getActivity())) {
            twitterLogin.twitterLogIn(loginButton);
        }
        return rootView;
    }
    // Checks if network connection avaliable  and if not show toast message
    private boolean isNetworkConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();
        if (ni == null) {
            Toast.makeText(context, getResources().getString(R.string.no_connection),
                    Toast.LENGTH_LONG).show();
            return false;
        } else
            return true;

    }

}
