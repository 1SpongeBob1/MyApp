package com.test.viewmodeldemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.os.Looper;
import android.widget.Toast;

import com.test.viewmodeldemo.databinding.ActivityMainBinding;

import org.greenrobot.eventbus.EventBus;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



    }

    @Override
    protected void onStart() {
        super.onStart();

        MainActivityViewModel viewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);
        viewModel.getContent().observe(this, content ->{
            binding.content.setText(content.toString());
        });

        binding.button.setOnClickListener(v->{
            viewModel.test ++;
            binding.content.setText(String.valueOf(viewModel.test));
        });

    }
}
