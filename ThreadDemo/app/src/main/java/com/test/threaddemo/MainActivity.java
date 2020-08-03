package com.test.threaddemo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.AsyncTaskLoader;

import android.content.Context;
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
    private List<String> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        list.add("1");
        list.add("2");
        list.add("3");

        t1.start();
        t2.start();
        t3.start();

    }

    Thread t1 = new Thread(()->{
//        int i = new Random().nextInt(3);
//        try {
//            Thread.sleep(i * 1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        synchronized (this){
//            while (publicNum < 10){
//                publicNum ++;
//                Log.d("test1", "publicNum在线程1里面加1 = " + publicNum );
//            }
        while (list.size() < 10){
            list.add(String.valueOf(list.size() + 1));
            Log.d("testList1", String.valueOf(list));
            Log.d("testList1", "list在thread1中添加" + (String.valueOf(list.size())) );
        }

        }

    });

    Thread t2 = new Thread(()->{
//        int i = new Random().nextInt(3);
//        try {
//            Thread.sleep(i * 1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        synchronized (this){
//            while (publicNum < 20){
//                publicNum ++;
//                Log.d("test2", "publicNum在线程2里面加1 = " + publicNum );
//            }
        while (list.size() < 20){
            list.add(String.valueOf(list.size() + 1));
            Log.d("testList2", String.valueOf(list));
            Log.d("testList2", "list在thread2中添加" + (String.valueOf(list.size())) );
        }

        }


    });

    Thread t3 = new Thread(()->{
//        int i = new Random().nextInt(3);
//        try {
//            Thread.sleep(i * 1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        synchronized (this){
//            while (publicNum < 30){
//                publicNum ++;
//                Log.d("test3", "publicNum在线程3里面加1 = " + publicNum );
//            }
        while (list.size() < 30){
            list.add(String.valueOf(list.size() + 1));
            Log.d("testList3", String.valueOf(list));
            Log.d("testList3", "list在thread3中添加" + (String.valueOf(list.size())) );
        }
        }


    });


}