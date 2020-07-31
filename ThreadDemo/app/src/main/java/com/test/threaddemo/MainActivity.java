package com.test.threaddemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.test.threaddemo.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private int publicNum = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        t1.start();
        t2.start();
        t3.start();

    }

    Thread t1 = new Thread(()->{
        int i = new Random().nextInt(3);
        try {
            Thread.sleep(i * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        while (publicNum < 100){
            publicNum ++;
            Log.d("test", "publicNum在线程1里面加1");
        }
    });

    Thread t2 = new Thread(()->{
        int i = new Random().nextInt(3);
        try {
            Thread.sleep(i * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        while (publicNum < 100){
            publicNum ++;
            Log.d("test", "publicNum在线程2里面加1");
        }
    });

    Thread t3 = new Thread(()->{
        int i = new Random().nextInt(3);
        try {
            Thread.sleep(i * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        while (publicNum < 100){
            publicNum ++;
            Log.d("test", "publicNum在线程3里面加1");
        }
    });


}
