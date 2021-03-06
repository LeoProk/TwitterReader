package com.example.leonid.twitterreader.Utilities;

import com.example.leonid.twitterreader.Interfaces.FactoryInterface;

import android.content.Context;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * Retrieve file from internal storage by name;
 */
final class FileGetter implements FactoryInterface {

    private String mFileName;

    private Context mContext;

    public FileGetter(Context context, String fileName) {

        mContext = context;
        mFileName = fileName;
    }

    @Override
    public Object doTask() {
        String temp = "";
        try {
            FileInputStream inputStream = mContext.openFileInput(mFileName);
            int c;

            while ((c = inputStream.read()) != -1) {
                temp = temp + Character.toString((char) c);
            }
            //string temp contains all the data of the file.
            inputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return temp;
    }
}
