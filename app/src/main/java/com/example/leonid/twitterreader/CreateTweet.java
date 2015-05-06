package com.example.leonid.twitterreader;

import java.util.ArrayList;

/**
 * Created by Leo on 2/4/2015.
 */
public class CreateTweet {
    private String title, thumbnailUrl, text, date;
    private ArrayList<String> genre;
    //getters and setter for adapter
    public CreateTweet() {
    }

    public CreateTweet(String name, String thumbnailUrl, String text, String date) {
        this.title = name;
        this.thumbnailUrl = thumbnailUrl;
        this.text = text;
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String name) {
        this.title = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getText() {
        return text;
    }

    public void setText(String textInfo) {
        this.text = textInfo;
    }


}