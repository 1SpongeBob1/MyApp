package com.test.addemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView tv;
    private static String content;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = (TextView)findViewById(R.id.text);
        button = (Button)findViewById(R.id.button);
        content = content + "\n" + "onCreate";

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, SecondActivity.class));
//                finish();
            }
        });

        tv.setText(content);
        Log.d("LifeCycle", "onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        content = content + "\n" + "onStart";
        tv.setText(content);
        Log.d("LifeCycle", "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        content = content + "\n" + "onResume";
        tv.setText(content);
        Log.d("LifeCycle", "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        content = content + "\n" + "onPause";
        tv.setText(content);
        Log.d("LifeCycle", "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        content = content + "\n" + "onStop";
        tv.setText(content);
        Log.d("LifeCycle", "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        content = content + "\n" + "onDestroy";
        tv.setText(content);
        Log.d("LifeCycle", "onDestroy");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        content = content + "\n" + "onRestart";
        tv.setText(content);
        Log.d("LifeCycle", "onRestart");
    }
}
