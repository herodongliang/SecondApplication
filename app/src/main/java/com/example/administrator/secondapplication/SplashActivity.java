package com.example.administrator.secondapplication;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;


public class SplashActivity extends BaseActivity {

    private SharedPreferences mSharedPreferences;
    private boolean mIsFirstIn;
    private static final String TAG = "SplashActivity";
    private Handler mjumpHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    Intent guideIntent = new Intent(SplashActivity.this,RegisterActivity.class);
                    startActivity(guideIntent);
                    finish();
                    break;
                case 0:
                    Intent mainIntent = new Intent(SplashActivity.this,MainActivity.class);
                    startActivity(mainIntent);
                    finish();
                    break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        initStatusBar();
        initPermissions(2,new String[]{Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE});
    }


//设置状态栏透明可见
    public void initStatusBar() {
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
    }
    public void initApplication() {
        //mSharedPreferences = getSharedPreferences("stategrid",MODE_PRIVATE);
       // mIsFirstIn = mSharedPreferences.getBoolean("first",true);
        mSharedPreferences = getSharedPreferences("token",MODE_PRIVATE);
        //SharedPreferences.Editor editor = mSharedPreferences.edit();
        String token = mSharedPreferences.getString("token","");
        Log.e(TAG, "initApplication: "+token);
        //mSharedPreferences.edit().putString("first","").commit();
        if (token!=null & token!=""){
            mjumpHandler.sendEmptyMessageDelayed(0,1000);
        }else {
            mjumpHandler.sendEmptyMessageDelayed(1,1000);
        }
    }

    @Override
    public void onPermissionSuccess(int requestCode) {
        initApplication();
    }

    @Override
    public void onPermissionFailure(int requestCode) {
        Toast.makeText(SplashActivity.this, "no permission", Toast.LENGTH_SHORT).show();
    }
}
