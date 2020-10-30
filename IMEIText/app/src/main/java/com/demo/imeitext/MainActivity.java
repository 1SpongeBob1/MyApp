package com.demo.imeitext;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.anythink.core.api.ATSDK;

public class MainActivity extends AppCompatActivity {
    public static final String APP_ID = "a5aa1f9deda26d";
    public static final String APP_KEY = "4f7b9ac17decb9babec83aac078742c7";
    public static final String REWARD_AD = "b5b728e7a08cd4";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ATSDK.init(getApplicationContext(), APP_ID, APP_KEY);
    }
}
