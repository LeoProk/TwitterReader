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

package com.example.leonid.twitterreader;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.example.leonid.twitterreader.Twitter.CreateTweet;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class CustomListAdapter extends BaseAdapter {

    ImageLoader mImageLoader = AppController.getInstance().getImageLoader();

    private Activity mActivity;

    private LayoutInflater mInflater;

    private List<CreateTweet> mNewsItems;

    public CustomListAdapter(Activity activity, List<CreateTweet> movieItems) {
        mActivity = activity;
        mNewsItems = movieItems;
    }

    @Override
    public int getCount() {
        return mNewsItems.size();
    }

    @Override
    public Object getItem(int location) {
        return mNewsItems.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (mInflater == null) {
            mInflater = (LayoutInflater) mActivity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.list_row, null);
        }

        if (mImageLoader == null) {
            mImageLoader = AppController.getInstance().getImageLoader();
        }
        NetworkImageView thumbNail = (NetworkImageView) convertView
                .findViewById(R.id.thumbnail);
        TextView title = (TextView) convertView.findViewById(R.id.title);
        TextView articalView = (TextView) convertView.findViewById(R.id.text);

        TextView date = (TextView) convertView.findViewById(R.id.date);
        // getting movie data for the row
        CreateTweet m = mNewsItems.get(position);

        // thumbnail image
        thumbNail.setImageUrl(m.getThumbnailUrl(), mImageLoader);

        // title
        title.setText(m.getTitle());
        articalView.setText(m.getText());
        date.setText(m.getDate());

        return convertView;
    }

}