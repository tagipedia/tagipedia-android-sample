package com.example.shimaahassan.testandroidlibrary;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.tagipedia.TUtils;
import com.tagipedia.tdata.contracts.Topic;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Bundle bundle = getIntent().getExtras();
        if(bundle != null) {
            HashMap topic = (HashMap) bundle.getSerializable("topic");
            if(topic != null) {
                TUtils.showAdDialog(this,topic);
            }
        }

        TUtils.showBluetoothDialog(this, "HELLLO" , "WORLD");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            TUtils.showLocationDialog(this, "Location Permission", "get location permission");
        }
    }

}