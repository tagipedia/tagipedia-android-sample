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
        Callback.OnMapButtonPressedListener onMapButtonPressedListener = new Callback.OnMapButtonPressedListener() {
            @Override
            public void onMapButtonPressed(HashMap hashMap) {
                System.out.print(hashMap);
                // dispatch to tagipedia maps to navigate to location should be like this
                // LinkedHashMap<String, Object> navigationParams = new LinkedHashMap<String, Object>();
                // navigationParams.put("route_to", (String)hashMap("booth_id"));
                // new HashMap<String, Object>(){{
                // put("type", "SHOW_NAVIGATION_DIALOG");
                // put("navigation_params", navigationParams);
                // }}
            }
        };
        tBuilder.setMapButtonPressedListener(onMapButtonPressedListener);
        tBuilder.build();
        //to register user with interests
        //this will show ads based on matching between ad interests and user interests otherwise it will show ads that was created without interests
        //String[] interests;
        tBuilder.identifyUser("USER_NAME","UUID", interests);
    }
}