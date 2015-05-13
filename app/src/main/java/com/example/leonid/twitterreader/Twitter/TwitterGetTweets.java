/*
 * Copyright (C) 2013 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.leonid.twitterreader.Twitter;

import com.example.leonid.twitterreader.Interfaces.OnTaskCompleted;

import android.os.AsyncTask;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

/**
 * This class query string using twitter4j and fetching results
 */
public class TwitterGetTweets extends AsyncTask<String, Void, List<List<String>>> {

    //dev info
    private static final String CONSUMER_KEY = "myT2UGT74XR7AIr9VpEaq1HWr";

    private static final String CONSUMER_SECRET
            = "IPrxSt5x8dSBr18FbykzBHk8ly2Iwfy6HuONblbW2ysDWfDyuH";

    private static final String ACCESS_KEY = "3216913431-gad2CG8aKCkLPb4f9CZELs4wtYISwgUXWI2kukC";

    private static final String ACCESS_SECRET = "N6g9GTTHeS0w7B0RsHYqdwiHcjXA7o9wOsQk8uqcPdh0j";

    QueryResult mResult;

    private OnTaskCompleted mListener;

    private List<List<String>> mTweetsInfo;

    public TwitterGetTweets(OnTaskCompleted listener) {
        mListener = listener;
    }

    @Override
    protected List<List<String>> doInBackground(String... params) {
        List<String> texts = new ArrayList<>();
        List<String> titles = new ArrayList<>();
        List<String> images = new ArrayList<>();
        List<String> date = new ArrayList<>();
        mTweetsInfo = new ArrayList<>();
        if (!isCancelled()) {
            ConfigurationBuilder cb = new ConfigurationBuilder();
            cb.setDebugEnabled(true)
                    .setOAuthConsumerKey(CONSUMER_KEY)
                    .setOAuthConsumerSecret(CONSUMER_SECRET)
                    .setOAuthAccessToken(ACCESS_KEY)
                    .setOAuthAccessTokenSecret(ACCESS_SECRET);
            TwitterFactory tf = new TwitterFactory(cb.build());
            Twitter twitter = tf.getInstance();
            //query search result
            Query query = new Query(params[0]);
            //how much tweets need to be displayed(max 200)
            query.count(200);
            try {
                mResult = twitter.search(query);
                for (twitter4j.Status status :  mResult.getTweets()) {
                    if (!isCancelled()) {
                        texts.add(status.getText());
                        titles.add(status.getUser().getName());
                        images.add(status.getUser().getBiggerProfileImageURL());
                        String cleanDate = status.getCreatedAt().toString();
                        date.add(cleanDate.substring(0, cleanDate.length() - 15) + " " + cleanDate
                                .substring(cleanDate.length() - 4));
                    }
                }
            } catch (TwitterException e) {
                Log.e("exeption", e.toString());
            }
            //loop teuth results and create array list for list view

            mTweetsInfo.add(titles);
            mTweetsInfo.add(date);
            mTweetsInfo.add(texts);
            mTweetsInfo.add(images);

        }
        return  mTweetsInfo;
    }

    @Override
    protected void onPostExecute(List<List<String>> nothing) {
        try {
            mListener.onTaskCompleted();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        super.onPostExecute( mTweetsInfo);

    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
        this.cancel(true);
    }
}

