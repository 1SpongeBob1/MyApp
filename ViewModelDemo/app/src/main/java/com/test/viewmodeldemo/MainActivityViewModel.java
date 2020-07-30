package com.test.viewmodeldemo;

import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MainActivityViewModel extends ViewModel {
    private MutableLiveData<Integer> content = new MutableLiveData<Integer>();
    private int i;
    public int test = 1;

    public MutableLiveData<Integer> getContent(){
        return content;
    }

    public MainActivityViewModel(){
        t.start();
        EventBus.getDefault().register(this);
        content.setValue(0);        //
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(Integer i){
        content.setValue(i);
    }

    @Override
    protected void onCleared(){
        super.onCleared();
        EventBus.getDefault().unregister(this);
    }

    Thread t = new Thread(()->{
        while (true){
            try {
                i ++;
                EventBus.getDefault().postSticky(i);
                Log.d("test", String.valueOf(i));
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    });


}
