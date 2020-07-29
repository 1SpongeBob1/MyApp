package com.test.viewmodeldemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.widget.Toast;

import com.test.viewmodeldemo.databinding.ActivityMainBinding;

import org.greenrobot.eventbus.EventBus;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Thread t = new Thread(()->{
            while (true){
                EventBus.getDefault().post(i);
                i ++;
//                Toast.makeText(MainActivity.this, String.valueOf(i), Toast.LENGTH_SHORT).show();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        t.start();

//        binding.button.setOnClickListener(v->{
//            t.start();
//            binding.button.setClickable(false);
//        });

        MainActivityViewModel viewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);
        viewModel.getContent().observe(this, content ->{
            if (binding.content != null ){
                binding.content.setText(content.toString());
            }else{
                Toast.makeText(this, "111", Toast.LENGTH_SHORT).show();
            }
            Toast.makeText(this, "222", Toast.LENGTH_SHORT).show();
        });

    }

}
