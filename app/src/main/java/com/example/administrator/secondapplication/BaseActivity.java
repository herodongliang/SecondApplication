package com.example.administrator.secondapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public abstract class BaseActivity extends AppCompatActivity {
    public static final int REQUEST_PERMISSION_SETTING = 9527;
    private String[] mPermissions;
    private static int mCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * 初始化权限，需手动调用
     */
    public void initPermissions(int requsetCode,String... permissions) {
        mPermissions = permissions;
        mCode = requsetCode;
        if (Build.VERSION.SDK_INT >= 23) {
            if (!hasPermisson(permissions)) {
                doWithRefused(permissions);
            } else {
                onPermissionSuccess(mCode);
            }
        } else {
            onPermissionSuccess(mCode);
        }
    }

    /**
     * 为子类提供权限检查
     *
     * @param permissions
     * @return
     */
    public boolean hasPermisson(String... permissions) {
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    /**
     * 为子类提供权限请求方法
     *
     * @param requsetCode
     * @param permissions
     */
    public void requestPermission(int requsetCode, String... permissions) {
        ActivityCompat.requestPermissions(this, permissions, requsetCode);
    }

    /**
     * 判断是否弹出对用户解释的对话框
     *
     * @param permissions
     * @return
     */
    public boolean shouldOpenExplanation(String... permissions) {
        for (String permission : permissions) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, permission)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 如果用户曾拒绝过一次权限申请，弹出对话框向用户解释所需权限，否则重新申请所需权限
     *
     * @param permissions
     */
    public void doWithRefused(String... permissions) {
        if (shouldOpenExplanation(permissions)) {
            openExplanation(mCode,permissions);
        } else {
            requestPermission(mCode, permissions);
        }
    }


    /**
     * 向用户解释为什么需要这些权限
     *
     * @param permissions
     */
    public void openExplanation(int requestCode,final String... permissions) {
        new AlertDialog.Builder(this)
                .setTitle("apply permission")
                .setCancelable(false)
                .setMessage("由于您拒绝了必要的权限，导致应用无法正常使用\n如若需要，请点击 【下一步】 重新进行授权")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        onPermissionFailure(mCode);
                    }
                })
                .setPositiveButton("下一步", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        requestPermission(mCode, permissions);
                    }
                })
                .show();
    }

    /**
     * 申请权限后执行的回调方法
     *
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        int count = 0;
        for (int i = 0; i < grantResults.length; i++) {
            if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                count++;
            }
        }
        //如果所有的权限都被授予了
        if (count == grantResults.length) {
            onPermissionSuccess(mCode);
        } else {
            //如果勾选了不再询问
            if (!shouldOpenExplanation(permissions)) {
                openPermissonSettings();
            } else {
                onPermissionFailure(mCode);
            }
        }

    }

    /**
     * 打开系统设置，手动设置权限
     */
    public void openPermissonSettings() {
        new AlertDialog.Builder(this)
                .setTitle("请手动设置权限")
                .setCancelable(false)
                .setMessage("由于您之前勾选了不再询问，请手动开启权限：\n\n请您在 设置/权限 勾选必需的权限")
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        onPermissionFailure(mCode);
                    }
                })
                .setPositiveButton("设置", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                        Uri uri = Uri.fromParts("package", getPackageName(), null);
                        intent.setData(uri);
                        startActivityForResult(intent, REQUEST_PERMISSION_SETTING);
                    }
                })
                .show();
    }

    /**
     * 跳到系统设置，返回后的处理
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == BaseActivity.REQUEST_PERMISSION_SETTING) {
            if (!hasPermisson(mPermissions)) {
                nothingDoWithPermissonSettings();
            } else {
                onPermissionSuccess(mCode);
            }
        }
    }


    /**
     * 打开系统设置后，没有全部授予相关权限
     */
    public void nothingDoWithPermissonSettings() {
        new AlertDialog.Builder(this)
                .setTitle("请手动设置权限")
                .setCancelable(false)
                .setMessage("您还没有授予相关权限，请手动开启：\n\n请您在 设置/权限 勾选必需的权限\n或：在 设置/存储 中选择 删除数据")
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        onPermissionFailure(mCode);
                    }
                })
                .setPositiveButton("设置", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                        Uri uri = Uri.fromParts("package", getPackageName(), null);
                        intent.setData(uri);
                        startActivityForResult(intent, REQUEST_PERMISSION_SETTING);
                    }
                })
                .show();
    }


    /**
     * 授权失败之后的操作
     */
    public abstract void onPermissionSuccess(int requestCode);


    /**
     * 授权失败之后的操作
     */
    public abstract void onPermissionFailure(int requestCode);

    /**
     * 初始化状态栏，根据需要调用
     */
    public void initStatusBar() {
        View decorView = getWindow().getDecorView();
        int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
        decorView.setSystemUiVisibility(option);
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
    }

}
