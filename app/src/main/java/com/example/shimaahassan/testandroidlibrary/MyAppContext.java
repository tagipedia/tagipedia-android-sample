package com.example.shimaahassan.testandroidlibrary;

import android.content.Context;
import android.content.Intent;
import android.support.multidex.MultiDexApplication;

import com.tagipedia.Callback;
import com.tagipedia.TBuilder;
import com.tagipedia.contracts.TRegion;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
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
                // navigationParams.put("route_to", (String)hashMap("feature_id"));
                // new HashMap<String, Object>(){{
                // put("type", "SHOW_NAVIGATION_DIALOG");
                // put("navigation_params", navigationParams);
                // }}
            }
        };
        //to receive when user enter beacon region
        Callback.OnEnterBeaconRegionListener onEnterBeaconRegion = new Callback.OnEnterBeaconRegionListener(){
            @Override
            public void onEnterBeaconRegion(HashMap data) {
                System.out.print(data);
            }
        };
        tBuilder.setEnterBeaconRegionListener(onEnterBeaconRegion);
        //to receive when user exit beacon region
        Callback.OnExitBeaconRegionListener onExitBeaconRegion = new Callback.OnExitBeaconRegionListener(){
            @Override
            public void onExitBeaconRegion(HashMap data) {
                System.out.print(data);
                //time_spent in milliseconds
                //enter_date and exit_date in millisecond since 1970
            }
        };
        tBuilder.setExitBeaconRegionListener(onExitBeaconRegion);
        JSONObject obj = null;
        ArrayList<TRegion> regions = new ArrayList<TRegion>();
        try {
            obj = new JSONObject(loadJSONFromAsset(getApplicationContext()));
            JSONArray arr = obj.getJSONArray("regions");
            for (int i = 0; i < arr.length(); i++)
            {
                String UUID = arr.getJSONObject(i).getString("UUID");
                String major = arr.getJSONObject(i).getString("major");
                String minor = arr.getJSONObject(i).getString("minor");
                regions.add(new TRegion(UUID, major, minor));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        if(regions.size() > 0){
            tBuilder.setRegions(regions);
        }
        tBuilder.setMapButtonPressedListener(onMapButtonPressedListener);
        tBuilder.build();
        //to register user with interests
        //this will show ads based on matching between ad interests and user interests otherwise it will show ads that was created without interests
        //String[] interests;
//        TBuilder.identifyUser("USER_NAME", interests);
    }

    public String loadJSONFromAsset(Context context) {
        String json = null;
        try {
            InputStream is = context.getAssets().open("TRegions.json");

            int size = is.available();

            byte[] buffer = new byte[size];

            is.read(buffer);

            is.close();

            json = new String(buffer, "UTF-8");


        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;

    }
}
