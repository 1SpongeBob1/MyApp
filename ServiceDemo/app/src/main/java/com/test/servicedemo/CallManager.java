package com.test.servicedemo;

import android.content.Context;
import android.media.AudioManager;
import android.telecom.Call;
import android.telecom.VideoProfile;

public class CallManager {
    public static Call call;

    private Context mContext;
    private AudioManager audioManager;

    public CallManager(Context context) {
        mContext = context;

        audioManager = (AudioManager) mContext.getSystemService(Context.AUDIO_SERVICE);
    }

    /**
     * 接听
     */
    public void answer(){
        if (call != null){
            call.answer(VideoProfile.STATE_AUDIO_ONLY);
        }
    }

    /**
     * 断开，包括挂断和拒接
     */
    public void disconnect(){
        if (call != null){
            call.disconnect();
        }
    }

    /**
     * 销毁资源
     */
    public void destroy(){
        audioManager = null;
        mContext = null;
        call = null;
    }

    /**
     * 打开免提
     */
    public void openSpeaker() {
        if (audioManager != null) {
//            audioManager.setMode(AudioManager.MODE_IN_CALL);
//            audioManager.setSpeakerphoneOn(true);
            audioManager.setMode(AudioManager.MODE_NORMAL);
            audioManager.setSpeakerphoneOn(true);
            audioManager.setStreamVolume(AudioManager.STREAM_VOICE_CALL,
                    audioManager.getStreamMaxVolume(AudioManager.STREAM_VOICE_CALL),
                    AudioManager.STREAM_VOICE_CALL);
        }
    }

}
