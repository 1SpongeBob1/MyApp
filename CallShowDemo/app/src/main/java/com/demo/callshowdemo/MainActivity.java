package com.demo.callshowdemo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.telecom.TelecomManager;
import android.view.WindowManager;
import android.widget.CompoundButton;
import android.widget.Toast;

import java.lang.reflect.Field;

public class MainActivity extends AppCompatActivity {

    private CompoundButton.OnCheckedChangeListener switchCallCheckChangeListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Android M 以上的系统则发起将本应用设为默认应用的请求
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M ){
            Intent intent = new Intent(TelecomManager.ACTION_CHANGE_DEFAULT_DIALER);
            intent.putExtra(TelecomManager.EXTRA_CHANGE_DEFAULT_DIALER_PACKAGE_NAME, getPackageName());
            startActivity(intent);
        }

        initView();
    }

    private void initView() {

        // 检查是否开启了权限
        switchCallCheckChangeListener = new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked && Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                        && !Settings.canDrawOverlays(MainActivity.this)
                ) {
                    // 请求 悬浮框 权限
                    MainActivity.this.askForDrawOverlay();

//                    // 未开启时清除选中状态，同时避免回调
//                    switchListenCall.setOnCheckedChangeListener(null);
//                    switchListenCall.setChecked(false);
//                    switchListenCall.setOnCheckedChangeListener(switchCallCheckChangeListener);
                    return;
                }
            }
        };
//        switchListenCall.setOnCheckedChangeListener(switchCallCheckChangeListener);
    }

    private void askForDrawOverlay() {
        AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setTitle("允许显示悬浮框")
                .setMessage("为了使电话监听服务正常工作，请允许这项权限")
                .setPositiveButton("去设置", (dialog, which) -> {
                    MainActivity.this.openDrawOverlaySettings();
                    dialog.dismiss();
                })
                .setNegativeButton("稍后再说", (dialog, which) -> dialog.dismiss())
                .create();

        //noinspection ConstantConditions
        alertDialog.getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);
        alertDialog.show();
    }

    /**
     * 跳转悬浮窗管理设置界面
     */
    private void openDrawOverlaySettings() {
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            // Android M 以上引导用户去系统设置中打开允许悬浮窗
            // 使用反射是为了用尽可能少的代码保证在大部分机型上都可用
            try {
                Context context = this;
                Class clazz = Settings.class;
                Field field = clazz.getDeclaredField("ACTION_MANAGE_OVERLAY_PERMISSION");
                Intent intent = new Intent(field.get(null).toString());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setData(Uri.parse("package:" + context.getPackageName()));
                context.startActivity(intent);
            } catch (Exception e) {
                Toast.makeText(this, "请在悬浮窗管理中打开权限", Toast.LENGTH_LONG).show();
            }
        }
    }
}
