package com.test.viewmodeldemo;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MainActivityViewModel extends ViewModel {
    private MutableLiveData<Integer> content =new MutableLiveData<Integer>();

    public MutableLiveData<Integer> getContent(){
        return content;
    }

    public MainActivityViewModel(){
        EventBus.getDefault().register(this);
        content.setValue(0);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(int i){
        content.setValue(i);
    }

    @Override
    protected void onCleared(){
        super.onCleared();
        EventBus.getDefault().unregister(this);
    }

}
