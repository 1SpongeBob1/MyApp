package com.demo.callshowdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.os.Build;
import android.telecom.Call;
import android.telecom.CallAudioState;
import android.telecom.InCallService;

import androidx.annotation.RequiresApi;

@RequiresApi(api = Build.VERSION_CODES.M)
public class CallingService extends InCallService {
    private BroadcastReceiver receiver = null;
    private Call.Callback mCallBack = new Call.Callback() {
        @Override
        public void onStateChanged(Call call, int state) {
            super.onStateChanged(call, state);
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
        registerMyReceiver();
    }

    private void registerMyReceiver() {
        receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                if (action.equals(Intent.ACTION_HEADSET_PLUG)) {
                    if (intent.hasExtra("state")){
                        if (intent.getIntExtra("state", 0) == 0){
                            CallingService.this.setAudioRoute(CallAudioState.ROUTE_SPEAKER);
                        } else if (intent.getIntExtra("state", 0) == 1){
                            CallingService.this.setAudioRoute(CallAudioState.ROUTE_WIRED_HEADSET);
                        }
                    }
                }
                IntentFilter filter = new IntentFilter();
                filter.addAction(Intent.ACTION_HEADSET_PLUG );
                registerReceiver(receiver, filter);
            }
        };
    }

    @Override
    public void onCallAdded(Call call) {
        super.onCallAdded(call);
        AudioManager manager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
            if ( manager.isWiredHeadsetOn() ){
                this.setAudioRoute(CallAudioState.ROUTE_WIRED_HEADSET);
            }else {
                this.setAudioRoute(CallAudioState.ROUTE_SPEAKER);
            }

            call.registerCallback(mCallBack);
            CallManager.mCall = call;
            CallType type = null;

            if (call.getState() == Call.STATE_RINGING){
                type = CallType.CALL_IN;
            }else if (call.getState() == Call.STATE_CONNECTING){
                type = CallType.CALL_OUT;
            }

            if (type != null){
                Call.Details details = call.getDetails();
                Intent intent = new Intent(this, CallingBackgroundActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra(Intent.EXTRA_MIME_TYPES, type);
                startActivity(intent);
            }
    }

    @Override
    public void onCallRemoved(Call call) {
        super.onCallRemoved(call);
        CallManager.mCall.unregisterCallback(mCallBack);
        CallManager.mCall = null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (receiver != null){
            unregisterReceiver(receiver);
        }
    }

    public enum CallType{
        CALL_IN,
        CALL_OUT
    }
}
