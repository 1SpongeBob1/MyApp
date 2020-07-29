package com.test.myapplication;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.test.myapplication.databinding.ActivityOkhttpConnectionBinding;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OkHttpConnectionActivity extends Activity {
    private ActivityOkhttpConnectionBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOkhttpConnectionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        new Thread(new Runnable(){
            @Override
            public void run() {
                //1、创建okHttpClient实例
                OkHttpClient client = new OkHttpClient();

                //2、创建request对象
                Request request = new  Request.Builder()
                        .url("http://www.baidu.com")        //3、对空的request对象填充参数、地址等
                        .build();

                //4、调用OkHttpClient的newCall()方法创建一个Call对象，并调用它的execute方法发送请求，并接收response数据
                String responseData;
                try {
                    Response response = client.newCall(request).execute();
                    //5、处理response数据
                    responseData = response.body().toString();
                    showContent(responseData);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

        }).start();
    }

    private void showContent(final String responseData){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                binding.okHttpResp.setText(String.valueOf(responseData));
            }
        });
    }
}
