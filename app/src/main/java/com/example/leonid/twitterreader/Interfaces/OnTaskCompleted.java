package com.example.leonid.twitterreader.Interfaces;

import java.util.concurrent.ExecutionException;

/**
 *interface for async task complementation
 */
public interface OnTaskCompleted {
    void onTaskCompleted() throws ExecutionException, InterruptedException;

}
