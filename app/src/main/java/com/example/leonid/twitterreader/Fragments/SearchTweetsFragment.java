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

package com.example.leonid.twitterreader.Fragments;

import com.example.leonid.twitterreader.CustomListAdapter;
import com.example.leonid.twitterreader.Interfaces.OnTaskCompleted;
import com.example.leonid.twitterreader.R;
import com.example.leonid.twitterreader.Twitter.TwitterGetTweets;
import com.example.leonid.twitterreader.Utilities.UtilitiesFactory;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;

import java.util.concurrent.ExecutionException;

/**
 * Creates tweets search fragment with get string for edit text send it TwitterGetTweets and
 * populate the list view with result
 */
public class SearchTweetsFragment extends Fragment implements OnTaskCompleted {

    private TwitterGetTweets mGetTweets;

    private View mRootView;

    private ListView mListView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        //set the view of the fragment
        mRootView = inflater.inflate(R.layout.search_fragment, container, false);
        //getting listview to desplay tweets
        mListView = (ListView) mRootView.findViewById(R.id.list);
        //getting edittext of the search value
        EditText search = (EditText) mRootView.findViewById(R.id.search_user);
        //get the tweets after text changed
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if ((boolean) UtilitiesFactory.checkNetwork(getActivity()).doTask()) {
                    // canceling asynctask if any was active
                    if (mGetTweets != null) {
                        mGetTweets.cancel(true);
                    }
                    mGetTweets = new TwitterGetTweets(SearchTweetsFragment.this);
                    mGetTweets.execute(s.toString() + " source:twitterfeed");
                }
            }
        });

        return mRootView;
    }

    //when task complete getting the array list for asynctask and put them in listview
    @Override
    public void onTaskCompleted() throws ExecutionException, InterruptedException {
        Activity host = (Activity) mRootView.getContext();
        CustomListAdapter adapter = new CustomListAdapter(host, mGetTweets.get());
        mListView.setAdapter(adapter);
    }
}
