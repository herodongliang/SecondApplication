package com.example.administrator.secondapplication;


import android.content.Context;
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
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.secondapplication.fourfragment.MeFragment;
import com.example.administrator.secondapplication.lrecyclertext.AppToast;
import com.example.administrator.secondapplication.lrecyclertext.BlankFragment;
import com.example.administrator.secondapplication.popfragment.TabView;
import com.example.administrator.secondapplication.popfragment.TabViewChild;
import com.example.administrator.secondapplication.secondfragment.SecondFragment;
import com.example.administrator.secondapplication.thirdfragment.ThirdFragment;
import com.example.administrator.secondapplication.utils.EnCodeUtils;
import com.example.administrator.secondapplication.utils.PopupMenuUtil;
import com.facebook.drawee.backends.pipeline.Fresco;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity {
    private RelativeLayout rlClick;
    private ImageView ivImg;
    private Context context;
    private TabView tabView;

    private static final String TAG = "MainActivity";
    public static String token;
    private TextView tv_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        initStatusBar();
        initViews();
        tv_title = (TextView) findViewById(R.id.tv_title);
        tabView = (TabView) findViewById(R.id.tabView);
        List<TabViewChild> tabViewChildList=new ArrayList<>();
        TabViewChild tabViewChild01=new TabViewChild(R.mipmap.main_moments_icon_selected,R.mipmap.main_moments_icon_unselected,"Moments",new BlankFragment());
        TabViewChild tabViewChild02=new TabViewChild(R.mipmap.main_colleagues_icon_selected,R.mipmap.main_colleagues_icon_unselected,"Colleagues",  new SecondFragment());
        TabViewChild tabViewChild03=new TabViewChild();
        TabViewChild tabViewChild04=new TabViewChild(R.mipmap.main_messages_icon_selected,R.mipmap.main_messages_icon_unselected,"Messages",new ThirdFragment());
        TabViewChild tabViewChild05=new TabViewChild(R.mipmap.main_me_icon_selected,R.mipmap.main_me_icon_unselected,"Me",  new MeFragment());
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

    private void initViews() {
        context = this;
        ivImg = (ImageView) findViewById(R.id.iv_img);
        rlClick = (RelativeLayout) findViewById(R.id.rl_click);
        rlClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenuUtil.getInstance()._show(context, ivImg);
            }
        });

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
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN && !PopupMenuUtil.getInstance()._isShowing()) {
            if ((System.currentTimeMillis() - exitTime) > 3000) {
                AppToast.makeShortToast(context,"再按一次退出程序");
                exitTime = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onBackPressed() {

        // 当popupWindow 正在展示的时候 按下返回键 关闭popupWindow 否则关闭activity
        if (PopupMenuUtil.getInstance()._isShowing()) {
            PopupMenuUtil.getInstance()._rlClickAction();
        }else {
            super.onBackPressed();
        }
    }

}
