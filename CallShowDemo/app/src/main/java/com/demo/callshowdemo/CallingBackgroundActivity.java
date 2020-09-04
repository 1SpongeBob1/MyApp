package com.demo.callshowdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.demo.callshowdemo.databinding.ActivityCallingBackgroundBinding;

public class CallingBackgroundActivity extends AppCompatActivity {
    private ActivityCallingBackgroundBinding binding;
    private CallManager mCallManager;
    private CallingService.CallType mCallType;
    private final static String CALL_END_ACTION = "com.action.CallEnd";

    private BroadcastReceiver myReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(CALL_END_ACTION)) {
                CallingBackgroundActivity.this.finish();
            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());

        binding.receive.setOnClickListener( v -> {
            mCallManager.answer();
        });

        binding.refuse.setOnClickListener(v -> {
            mCallManager.disconnect();
            CallingBackgroundActivity.this.finish();
        });

        IntentFilter filter = new IntentFilter();
        filter.addAction(CALL_END_ACTION);
        registerReceiver(myReceiver, filter);

        mCallManager = new CallManager(this);
        if (getIntent() != null){
            mCallType = (CallingService.CallType) getIntent().getSerializableExtra(Intent.EXTRA_MIME_TYPES);
        }
        
        initView();
    }

    private void initView() {
        int uiOptions = View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION //hide navigationBar
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
        getWindow().getDecorView().setSystemUiVisibility(uiOptions);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);

        // 打进的电话
        if (mCallType == CallingService.CallType.CALL_IN) {
            binding.receive.setVisibility(View.VISIBLE);
        }
        // 打出的电话
        else if (mCallType == CallingService.CallType.CALL_OUT) {
            binding.receive.setVisibility(View.GONE);
//            callManager.openSpeaker();
        }

        showOnLockScreen();
    }

    public void showOnLockScreen() {
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON |
                        WindowManager.LayoutParams.FLAG_FULLSCREEN |
                        WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED |
                        WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON,
                WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON |
                        WindowManager.LayoutParams.FLAG_FULLSCREEN |
                        WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED |
                        WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mCallManager.destroy();
    }
}
