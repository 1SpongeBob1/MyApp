package com.test.servicedemo;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class CallActivity extends AppCompatActivity implements View.OnClickListener{
    private CallManager callManager;
    private String number;
    private MyService.CallType callType;
    private Button receive;
    private Button refuse;
    private final static String CALL_END_ACTION = "com.action.CallEnd";

    private BroadcastReceiver myReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(CALL_END_ACTION)) {
                CallActivity.this.finish();
            }
        }
    };

//    public static void actionStart(Context context, String number, MyService.CallType callType) {
//        Intent i = new Intent(context, CallActivity.class);
//        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        i.putExtra(Intent.EXTRA_MIME_TYPES, callType);
//        i.putExtra(Intent.EXTRA_PHONE_NUMBER, number);
//        context.startActivity(i);
//    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call);

//        ActivityStack.getInstance().addActivity(this);

        receive = (Button)findViewById(R.id.receive);
        refuse = (Button)findViewById(R.id.refuse);
        receive.setOnClickListener(this);
        refuse.setOnClickListener(this);

        IntentFilter filter = new IntentFilter();
        filter.addAction(CALL_END_ACTION);
        registerReceiver(myReceiver, filter);

        callManager = new CallManager(this);
        if (getIntent() != null){
            callType = (MyService.CallType) getIntent().getSerializableExtra(Intent.EXTRA_MIME_TYPES);
        }

        initView();

    }

    private void initView(){
        int uiOptions = View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION //hide navigationBar
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
        getWindow().getDecorView().setSystemUiVisibility(uiOptions);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);

        // 打进的电话
        if (callType == MyService.CallType.CALL_IN) {
            receive.setVisibility(View.VISIBLE);
        }
        // 打出的电话
        else if (callType == MyService.CallType.CALL_OUT) {
            receive.setVisibility(View.GONE);
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
    public void onClick(View view) {
        if (view.getId() == R.id.receive) {
            callManager.answer();
            receive.setVisibility(View.GONE);

        } else if (view.getId() == R.id.refuse) {
            callManager.disconnect();
            finish();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        callManager.destroy();
    }
}
