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

/**
 * create new entery for list adapter
 */
public class CreateTweet {

    private String mTitle, mThumbnailUrl, mText, mDate;

    //getters and setter for adapter
    public CreateTweet(String title, String thumbnailUrl, String text, String date) {
        mTitle = title;
        mThumbnailUrl = thumbnailUrl;
        mText = text;
        mDate = date;

    }

    public String getTitle() {
        return mTitle;
    }

    public String getDate() {
        return mDate;
    }


    public String getThumbnailUrl() {
        return mThumbnailUrl;
    }


    public String getText() {
        return mText;
    }


}