package com.test.fragmentdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.WallpaperManager;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.test.fragmentdemo.service.MyWallpaperService;

import java.util.List;

import static androidx.navigation.Navigation.findNavController;

public class MainActivity extends AppCompatActivity {
    public static final int REQUEST_SET_LIVE_WALLPAPER = 152;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Dexter.withActivity(this)
                .withPermissions(
                        Manifest.permission.ACCESS_NETWORK_STATE,
                        Manifest.permission.WRITE_CONTACTS,
                        Manifest.permission.READ_CONTACTS
                ).withListener(new MultiplePermissionsListener() {
            @Override
            public void onPermissionsChecked(MultiplePermissionsReport report) {

            }

            @Override
            public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {

            }
        }).check();
        findViewById(R.id.testBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Intent intent = new Intent(WallpaperManager.ACTION_CHANGE_LIVE_WALLPAPER);
                intent.putExtra( WallpaperManager.EXTRA_LIVE_WALLPAPER_COMPONENT,
                        new ComponentName(MainActivity.this, MyWallpaperService.class) );
                startActivityForResult(intent, REQUEST_SET_LIVE_WALLPAPER);
            }
        });
    }

    //onSupportNavigateUp()方法的重写，意味着Activity将它的 back键点击事件的委托出去，如果当前并非栈中顶部的Fragment, 那么点击back键，返回上一个Fragment。
    @Override
    public boolean onSupportNavigateUp() {
        findNavController(this, R.id.nav_host_fragment).navigateUp();
        return super.onSupportNavigateUp();
    }
}
