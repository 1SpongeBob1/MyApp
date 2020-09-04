package com.test.servicedemo;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
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
public class MyService extends InCallService {
    private BroadcastReceiver headsetReceiver = null;

    private Call.Callback mCall = new Call.Callback(){
        @Override
        public void onStateChanged(Call call, int state) {
            super.onStateChanged(call, state);
                switch (state){
                    case Call.STATE_ACTIVE : {      //通话中
                        break;
                    }
                    case Call.STATE_DISCONNECTED : {    //通话结束
                        ActivityStack.getInstance().finishActivity(CallActivity.class);
                        break;
                    }
                }

        }
    };

    @Override
    public void onCallAdded(Call call) {
        super.onCallAdded(call);
        AudioManager audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        if (audioManager.isWiredHeadsetOn()) {
            this.setAudioRoute(CallAudioState.ROUTE_WIRED_HEADSET) ;
        } else {
            this.setAudioRoute( CallAudioState.ROUTE_SPEAKER);
        }

        call.registerCallback(mCall);
        CallManager.call = call;

        CallType callType = null;

        if (call.getState() == Call.STATE_RINGING){
            callType = CallType.CALL_IN;
        }else if (call.getState() == Call.STATE_CONNECTING){
            callType = CallType.CALL_OUT;
        }

        if (callType != null){
            Call.Details details = call.getDetails();
            String number = details.getHandle().toString().substring(4).replace("%20", "");
//            CallActivity.actionStart(this, number, callType);
            Intent intent = new Intent(getBaseContext(), CallActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra(Intent.EXTRA_MIME_TYPES, callType);
            startActivity(intent);
        }

    }

    @Override
    public void onCallRemoved(Call call) {
        super.onCallRemoved(call);
        call.unregisterCallback(mCall);
        CallManager.call = null;
    }

    @Override
    public void onCallAudioStateChanged(CallAudioState audioState) {
        super.onCallAudioStateChanged(audioState);
    }


    @Override
    public void onCreate() {
        super.onCreate();
        registerHeadsetReceiver();
    }

    @Override
    public ComponentName startService(Intent service) {
        return super.startService(service);
    }

    private void registerHeadsetReceiver() {
        headsetReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                if (action.equals(Intent.ACTION_HEADSET_PLUG)) {
                    if (intent.hasExtra("state")){
                        if (intent.getIntExtra("state", 0) == 0){
                            MyService.this.setAudioRoute(CallAudioState.ROUTE_SPEAKER);
                        } else if (intent.getIntExtra("state", 0) == 1){
                            MyService.this.setAudioRoute(CallAudioState.ROUTE_WIRED_HEADSET);
                        }
                    }
                }
            }
        };
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_HEADSET_PLUG );
        registerReceiver(headsetReceiver, filter);
    }

    @Override
    public void onDestroy() {
        if (headsetReceiver != null) {
            unregisterReceiver(headsetReceiver);
        }
        super.onDestroy();
    }


    public enum CallType{
        CALL_IN,
        CALL_OUT
    }
}
