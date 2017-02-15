package com.example.administrator.secondapplication;


import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.secondapplication.lrecyclertext.BlankFragment;
import com.example.administrator.secondapplication.secondfragment.SecondFragment;
import com.example.administrator.secondapplication.utils.EnCodeUtils;
import com.ycl.tabview.library.TabView;
import com.ycl.tabview.library.TabViewChild;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity {

    private TabView tabView;

    private static final String TAG = "MainActivity";
    public static String token;
    private TextView tv_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initStatusBar();
        tv_title = (TextView) findViewById(R.id.tv_title);
        tabView = (TabView) findViewById(R.id.tabView);
        List<TabViewChild> tabViewChildList=new ArrayList<>();
        TabViewChild tabViewChild01=new TabViewChild(R.mipmap.main_moments_icon_selected,R.mipmap.main_moments_icon_unselected,"Moments",new BlankFragment());
        TabViewChild tabViewChild02=new TabViewChild(R.mipmap.main_colleagues_icon_selected,R.mipmap.main_colleagues_icon_unselected,"Colleagues",  new SecondFragment());
        TabViewChild tabViewChild03=new TabViewChild(R.mipmap.bg_add_pic,R.mipmap.add,"Feedback",  new BlankFragment());
        TabViewChild tabViewChild04=new TabViewChild(R.mipmap.main_messages_icon_selected,R.mipmap.main_messages_icon_unselected,"Messages",new SecondFragment());
        TabViewChild tabViewChild05=new TabViewChild(R.mipmap.main_me_icon_selected,R.mipmap.main_me_icon_unselected,"Me",  new BlankFragment());
        tabViewChildList.add(tabViewChild01);
        tabViewChildList.add(tabViewChild02);
        tabViewChildList.add(tabViewChild03);
        tabViewChildList.add(tabViewChild04);
        tabViewChildList.add(tabViewChild05);
        tabView.setTabViewChild(tabViewChildList,getSupportFragmentManager());
        tabView.setOnTabChildClickListener(new TabView.OnTabChildClickListener() {
            @Override
            public void onTabChildClick(int position, ImageView imageView, TextView textView) {
                switch (position){
                    case 0:
                        tv_title.setText("Moments");
                        break;
                    case 1:
                        tv_title.setText("Colleagues");
                        break;
                    case 2:
                        tv_title.setText("Feedback");
                        break;
                    case 3:
                        tv_title.setText("Messages");
                        break;
                    case 4:
                        tv_title.setText("Me");
                        break;
                }
            }
        });
        Intent intent=getIntent();
        int id =intent.getIntExtra("first",-1);
        if(id==1){
            tabView.setTabViewDefaultPosition(0);
        }
        getIntenttoken();
    }
    private void getIntenttoken() {

        SharedPreferences mSharedPreferences = getSharedPreferences("token",MODE_PRIVATE);
        String gettoken = mSharedPreferences.getString("token","");
        String getemail = mSharedPreferences.getString("email","");
        byte[] bytes = EnCodeUtils.base64Encode(getemail+":"+gettoken);
        Log.e(TAG, "getIntenttoken: "+getemail+gettoken);
        token = "Basic "+new String(bytes);
        Log.e(TAG, "getIntenttoken11111111111111111111: "+token);
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

    }

    private long exitTime = 0;
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN ) {
            if ((System.currentTimeMillis() - exitTime) > 3000) {
                Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
