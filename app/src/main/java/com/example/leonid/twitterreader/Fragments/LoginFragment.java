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

import com.example.leonid.twitterreader.R;
import com.example.leonid.twitterreader.Twitter.TwitterAPI;
import com.example.leonid.twitterreader.Utilities.UtilitiesFactory;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 *
 */
public class LoginFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.login_fragment, container, false);
        final TwitterLoginButton mLoginButton = (TwitterLoginButton) rootView
                .findViewById(R.id.twitter_login_button);
        if ((boolean) UtilitiesFactory.checkNetwork(getActivity()).doTask()) {
            TwitterAPI.getLogin(mLoginButton).doTask();
        }
        return rootView;
    }

}
