package com.example.leonid.twitterreader.Fragments;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.leonid.twitterreader.CreateTweet;
import com.example.leonid.twitterreader.CustomListAdapter;
import com.example.leonid.twitterreader.Interfaces.OnTaskCompleted;
import com.example.leonid.twitterreader.R;
import com.example.leonid.twitterreader.Twitter.TwitterGetTweets;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by Leo on 4/29/2015.
 */
public class SearchTweetsFragment extends Fragment implements OnTaskCompleted {

    private List<List<String>> getTweetsInfo;
    private TwitterGetTweets getTweets;
    private View rootView;
    private ListView listView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //set the view of the fragment
        rootView = inflater.inflate(R.layout.search_fragment, container, false);
        //getting listview to desplay tweets
        listView = (ListView) rootView.findViewById(R.id.list);
        //getting edittext of the search value
        EditText search = (EditText) rootView.findViewById(R.id.search_user);
        //get the tweets after thext changed
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (isNetworkConnected(getActivity())){
                // canceling asynctask if any was active
                if(getTweets!=null){
                getTweets.cancel(true);
                }
                getTweets = new TwitterGetTweets(SearchTweetsFragment.this);
                getTweets.execute(s.toString()+" source:twitterfeed");
            }
    }
        });


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
    //when task complete getting the array list for asynctask and put them in listview
    @Override
    public void onTaskCompleted() throws ExecutionException, InterruptedException {
        List<List<String>> getTweetsInfo = getTweets.get();
        List<CreateTweet>  newsList = new ArrayList<>();
        for (int i = 0; i < getTweetsInfo.get(3).size(); i++) {
            CreateTweet news = new CreateTweet();
            news.setThumbnailUrl(getTweetsInfo.get(3).get(i));
            news.setTitle(getTweetsInfo.get(0).get(i));
            news.setText(getTweetsInfo.get(2).get(i));
            news.setDate(getTweetsInfo.get(1).get(i));
            newsList.add(news);
        }
        Activity host = (Activity) rootView.getContext();
        CustomListAdapter adapter = new CustomListAdapter(host, newsList);
        listView.setAdapter(adapter);
    }
}
