package com.test.threaddemo;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.test.threaddemo.databinding.ActivitySecondBinding;

public class SecondActivity extends Activity {
    private ActivitySecondBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySecondBinding.inflate(getLayoutInflater());


    }
}
