package com.example.leonid.twitterreader.Twitter;

import android.content.Context;
import android.util.Log;

import com.example.leonid.twitterreader.Interfaces.OnTaskCompleted;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;

import java.util.concurrent.ExecutionException;

import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.User;
import twitter4j.conf.ConfigurationBuilder;

/**
 * This class contain twitter login code
 */
public class TwitterLogin {
    private Context context;
    public TwitterLogin(Context context){
        this.context = context;
    }


    public void twitterLogIn(TwitterLoginButton loginButton){
        loginButton.setCallback(new Callback<TwitterSession>() {
            @Override
            public void success(Result<TwitterSession> result) {
            }

            @Override
            public void failure(TwitterException exception) {
                // Do something on failure
            }
        });

    }

}
