package com.test.servicedemo;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.app.role.RoleManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.telecom.TelecomManager;
import android.util.Log;
import android.view.WindowManager;
import android.widget.CompoundButton;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.lang.reflect.Field;

public class MainActivity extends AppCompatActivity {
//    private Switch switchPhoneCall;

    //    private Switch switchListenCall;
    private static final int REQUEST_CODE = 1;
    private static final String TAG = "CallShow_MainActivity";
    int sdk = Build.VERSION.SDK_INT;
    private CompoundButton.OnCheckedChangeListener switchCallCheckChangeListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.v(TAG, "sdk version = " + sdk);
        //Android M 以上的系统则发起将本应用设为默认应用的请求
        if (sdk >= Build.VERSION_CODES.M){// && Build.VERSION.SDK_INT < Build.VERSION_CODES.Q
            Intent intent = new Intent(TelecomManager.ACTION_CHANGE_DEFAULT_DIALER);
            intent.putExtra(TelecomManager.EXTRA_CHANGE_DEFAULT_DIALER_PACKAGE_NAME, getPackageName());
            startActivity(intent);
        }
        if (sdk >= Build.VERSION_CODES.Q){
            RoleManager manager = (RoleManager)getSystemService(Context.ROLE_SERVICE);
            Intent intent = manager.createRequestRoleIntent(RoleManager.ROLE_DIALER);
//            intent.setAction(TelecomManager.ACTION_CHANGE_DEFAULT_DIALER);
//            intent.putExtra(TelecomManager.EXTRA_CHANGE_DEFAULT_DIALER_PACKAGE_NAME,
//                    getPackageName());
            Log.v(TAG, "RoleManager.ROLE_DIALER = " + manager.isRoleAvailable(RoleManager.ROLE_DIALER));        //是否是合格role应用
            Log.v(TAG, RoleManager.ROLE_DIALER + " = " + manager.isRoleHeld(RoleManager.ROLE_DIALER));          //是否是默认role应用
            startActivityForResult(intent, REQUEST_CODE);
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
                    return;
                }
                Intent callListener = new Intent(MainActivity.this, CallListenerService.class);
                if (isChecked) {
                    startService(callListener);
                    Toast.makeText(MainActivity.this, "电话监听服务已开启", Toast.LENGTH_SHORT).show();
                } else {
                    stopService(callListener);
                    Toast.makeText(MainActivity.this, "电话监听服务已关闭", Toast.LENGTH_SHORT).show();
                }
            }
        };
//        switchListenCall.setOnCheckedChangeListener(switchCallCheckChangeListener);
    }

    private void askForDrawOverlay() {
        AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setTitle("允许显示悬浮框")
                .setMessage("为了使电话监听服务正常工作，请允许这项权限")
                .setPositiveButton("去设置", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        MainActivity.this.openDrawOverlaySettings();
                        dialog.dismiss();
                    }
                })
                .setNegativeButton("稍后再说", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
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
        Log.v(TAG, "openDrawOverlaySetting");
        if (sdk >= Build.VERSION_CODES.M && sdk < Build.VERSION_CODES.Q) {
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

    @Override
    protected void onResume() {
        super.onResume();

//        switchPhoneCall.setChecked(isDefaultPhoneCallApp());
//        switchListenCall.setChecked(isServiceRunning(CallListenerService.class));
    }

    /**
     * Android M 及以上检查是否是系统默认电话应用
     */
    public boolean isDefaultPhoneCallApp() {
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            TelecomManager manger = (TelecomManager) getSystemService(TELECOM_SERVICE);
            if (manger != null && manger.getDefaultDialerPackage() != null) {
                return manger.getDefaultDialerPackage().equals(getPackageName());
            }
        }
        return false;
    }

    public boolean isServiceRunning(Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        if (manager == null) return false;

        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }

        return false;
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        Log.v(TAG, "onActivityResult");
//        if (requestCode == REQUEST_CODE){
////            startService(new Intent(MainActivity.this, MyService.class));
//        }
//    }
}
