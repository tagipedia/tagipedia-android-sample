package com.example.shimaahassan.testandroidlibrary;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.tagipedia.TUtils;

public class MainActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Bundle bundle = getIntent().getExtras();
        if(bundle != null) {
            System.out.println("Topic " + bundle.getSerializable("topic"));
        }
        TUtils.showBluetoothDialog(this, "HELLLO" , "WORLD");
        TUtils.showLocationDialog(this, "Location Permission", "get location permission");
    }

}