package com.example.colin.gwentsite;

import android.app.IntentService;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.content.LocalBroadcastManager;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by Colin on 7/6/2017.
 */
public class RetrieveCardsIntentService extends IntentService {
    public RetrieveCardsIntentService(String name) {
        super(name);
    }
    public RetrieveCardsIntentService() {
        super("cardPageIntent");
    }
    @Override
    protected void onHandleIntent(Intent workIntent) {
        try {
            //Gets data from the incoming intent
            String data = workIntent.getDataString();
            String returnVal = HttpsHelper.getHttpsRequest(data);
            /*
             * Creates a new Intent containing a Uri object
             * BROADCAST_ACTION is a custom Intent action
            */
            Intent localIntent = new Intent(Constants.BROADCAST_ACTION)
                            // Puts the status into the Intent
                            .putExtra(Constants.CARD_PAGE_RESULT, returnVal);
            // Broadcasts the Intent to receivers in this app.
            LocalBroadcastManager.getInstance(this).sendBroadcast(localIntent);
        }
        catch (Exception e) {
            //Do something?
        }
    }
}
