package com.bryanpotts.mondayschild;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.net.MalformedURLException;

/**
 * Created by bryan on 29/10/14.
 */
public class mcAsyncRSSParser extends AsyncTask<String, Integer, mcRSSDataItem> {

    private Context appContext;
    private String urlRSSToParse;

    public mcAsyncRSSParser(Context currentAppContext, String urlRSS) {

        appContext = currentAppContext;
        urlRSSToParse = urlRSS;
    }

    // A callback method executed on UI thread on starting the task
    @Override
    protected void onPreExecute() {
        // Message to indicate start of parsing
        Toast.makeText(appContext, "Parsing started!", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected  mcRSSDataItem doInBackground(String...params) {

        mcRSSDataItem parsedData;
        mcRSSParser rssParser = new mcRSSParser();
        try {
            rssParser.parseRSSData(urlRSSToParse);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        parsedData = rssParser.getRSSDataItem();

        return parsedData;
    }

    // A callback method executed on UI thread, invoked after the completion of the task
    // When doInBackground has completed, the return value from that method is passed into
    // this event
    // handler
    @Override
    protected void onPostExecute(mcRSSDataItem result) {
        // Message to indicated end of parsing
        Toast.makeText(appContext, "Parsing finished!", Toast.LENGTH_SHORT).show();
    }

}
