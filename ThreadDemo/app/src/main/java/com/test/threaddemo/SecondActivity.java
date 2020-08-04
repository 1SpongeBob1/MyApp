package com.test.threaddemo;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.test.threaddemo.databinding.ActivitySecondBinding;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class SecondActivity extends Activity {
    private ActivitySecondBinding binding;
    private List<Integer> msgQueue = new LinkedList<>();
    private Handler handler;
    private static int code = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySecondBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        msgQueue.add(1);
        msgQueue.add(2);
        msgQueue.add(3);
        msgQueue.add(4);

        HandlerThread pushEvents = new HandlerThread("上报事件");
        pushEvents.start();
        handler = new Handler(pushEvents.getLooper()){
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                if (msg.what == code){
                    handler.post(r);
                }
            }
        };
        sendEvents.start();
        sendEvents_2.start();

    }

    Thread sendEvents = new Thread(() -> {
        while (true){
            try {
                Random r = new Random();
                int i = r.nextInt(5) + 1;
                Thread.sleep(i * 1000);
                //发送消息
                synchronized (msgQueue){
                    msgQueue.add(EventType.getType(i));
                    handler.removeMessages(code);
                    handler.sendEmptyMessage(code);
                    Log.d("test-send", "添加消息" + i + "\n" + msgQueue);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    });

    Thread sendEvents_2 = new Thread(() -> {
        while (true){
            try {
                Random r = new Random();
                int i = r.nextInt(5) + 1;
                Thread.sleep(i * 1000);
                //发送消息
                synchronized (msgQueue){
                    msgQueue.add(EventType.getType(i));
                    handler.removeMessages(code);
                    handler.sendEmptyMessage(code);
                    Log.d("test-send_2", "添加消息" + i + "\n" + msgQueue);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    });

    Runnable r = () -> {
        List<Integer> list;
        //获取消息集合
        synchronized (msgQueue){
            if (msgQueue.size() == 0){
                return;
            }
            list = new LinkedList<>(msgQueue);
            msgQueue.clear();
            Log.d("test-push", "----------------准备事件上报------------------");
        }

        //上报事件
        try {
            //模拟网络请求
            Random r = new Random();
            int i = r.nextInt(10) + 1;
            Thread.sleep(i * 1000);
            if (i > 8){
                Log.d("test-push", "--------------------发送失败-----------------------");
            }else {
                list.clear();
                Log.d("test-push", "--------------------发送成功----------------------");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (list.size() != 0){
            synchronized (msgQueue){
                msgQueue.addAll(0, list);
                Log.d("test-push", "------------------有消息未发送成功----------------" + list + "\n" + msgQueue);
            }
        }
        Log.d("test-push", "-------------------事件上报完成--------------------");
    };

}
