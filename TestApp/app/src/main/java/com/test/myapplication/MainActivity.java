package com.test.myapplication;

import androidx.annotation.MainThread;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.view.View;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.test.myapplication.databinding.ActivityMainBinding;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.launch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendRequestByHttpURLConnection();
            }
        });

        binding.nextPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, OkHttpConnectionActivity.class));
            }
        });
    }

    private void sendRequestByHttpURLConnection(){
//        Toast.makeText(MainActivity.this, "test2222", Toast.LENGTH_SHORT).show();
        new Thread(){
            @Override
            public void run() {
                super.run();
                StringBuilder stringBuilder = new StringBuilder();
                HttpURLConnection connection;
                try {
                    URL url = new URL("https://www.baidu.com/");
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);
                    InputStream input = connection.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                    String s;
                    while ( (s = reader.readLine()) != null){
                        stringBuilder.append(s);
                    }
                    showResponse(stringBuilder.toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }.start();
    }

    private void showResponse(final String responseContent){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                binding.response.setText(responseContent);
            }
        });
    }
}
