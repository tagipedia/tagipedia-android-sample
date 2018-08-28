package com.example.shimaahassan.testandroidlibrary;

import android.content.Intent;
import android.support.multidex.MultiDexApplication;

import com.tagipedia.Callback;
import com.tagipedia.TBuilder;

import java.util.HashMap;
/**
 * Created by tagipedia on 2/20/18.
 */

public class MyAppContext extends MultiDexApplication {
    public void onCreate() {
        super.onCreate();
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        TBuilder tBuilder = new TBuilder(this, "", "", "", "");
        tBuilder.setIntent(intent);
        tBuilder.setSmallIcon(R.drawable.ic_launcher);
        tBuilder.setDifferentBeaconNotifyPeriod(1000);
        tBuilder.setSameBeaconNotifyPeriod(20000);
        Callback.OnLoggedEventRecordListener loggedEventRecordListener = new Callback.OnLoggedEventRecordListener() {
            @Override
            public void onEventLoggedRecord(HashMap hashMap) {
                System.out.print(hashMap);
            }
        };
        tBuilder.setEventLoggerListener(loggedEventRecordListener);
        tBuilder.build();
    }
}