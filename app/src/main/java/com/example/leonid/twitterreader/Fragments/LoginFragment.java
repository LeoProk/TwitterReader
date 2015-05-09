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

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.leonid.twitterreader.R;
import com.example.leonid.twitterreader.Twitter.TwitterLogin;
import com.example.leonid.twitterreader.VolleyApi.NetworkCheck;
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
        NetworkCheck networkCheck = new NetworkCheck(getActivity());
        if(networkCheck.isNetworkConnected()) {
            twitterLogin.twitterLogIn(loginButton);
        }
        return rootView;
    }

}
