package com.test.viewmodeldemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import com.test.viewmodeldemo.databinding.ActivityMainBinding;


//import static com.Tools.postHttp;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

//        postHttp("", "");

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
