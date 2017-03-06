package com.example.administrator.secondapplication.utils;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Environment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.administrator.secondapplication.MainActivity;
import com.example.administrator.secondapplication.R;
import com.example.administrator.secondapplication.lrecyclertext.AppToast;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.File;

import okhttp3.Call;


/**
 * Created by dl
 * on 2016/11/22.
 */
public class PopupMenuUtil {

    private static final String TAG = "PopupMenuUtil";

    public static PopupMenuUtil getInstance() {
        return MenuUtilHolder.INSTANCE;
    }

    private static class MenuUtilHolder {
        public static PopupMenuUtil INSTANCE = new PopupMenuUtil();
    }

    private View rootVew;
    private PopupWindow popupWindow;

    private RelativeLayout rlClick;
    private ImageView ivBtn;
    private RelativeLayout llTest1, llTest2, llTest3, llTest4, llTest5, llTest6, llTest7, llTest8;

    /**
     * 动画执行的 属性值数组
     */
    float animatorProperty[] = null;
    /**
     * 第一排图 距离屏幕底部的距离
     */
    int top = 0;
    /**
     * 第二排图 距离屏幕底部的距离
     */
    int bottom = 52;

    /**
     * 创建 popupWindow 内容
     *
     * @param context context
     */
    private void _createView(final Context context) {
        rootVew = LayoutInflater.from(context).inflate(R.layout.popup_menu, null);
        rootVew.getBackground().setAlpha(200);
        popupWindow = new PopupWindow(rootVew,
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        //设置为失去焦点 方便监听返回键的监听
        popupWindow.setFocusable(false);

        // 如果想要popupWindow 遮挡住状态栏可以加上这句代码
        //popupWindow.setClippingEnabled(false);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setOutsideTouchable(false);

        if (animatorProperty == null) {
            top = dip2px(context, 310);
            bottom = dip2px(context, 210);
            animatorProperty = new float[]{bottom, 60, -30, -20 - 10, 0};
        }

        initLayout(context);
    }

    /**
     * dp转化为px
     *
     * @param context  context
     * @param dipValue dp value
     * @return 转换之后的px值
     */
    public static int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    /**
     * 初始化 view
     */
    private void initLayout(Context context) {
        rlClick = (RelativeLayout) rootVew.findViewById(R.id.pop_rl_click);
        ivBtn = (ImageView) rootVew.findViewById(R.id.pop_iv_img);
        llTest1 = (RelativeLayout) rootVew.findViewById(R.id.pop_ask_click);
        llTest2 = (RelativeLayout) rootVew.findViewById(R.id.pop_question_click);
        llTest3 = (RelativeLayout) rootVew.findViewById(R.id.pop_likes_click);
//        llTest4 = (LinearLayout) rootVew.findViewById(R.id.test4);
//        llTest5 = (LinearLayout) rootVew.findViewById(R.id.test5);
//        llTest6 = (LinearLayout) rootVew.findViewById(R.id.test6);
//        llTest7 = (LinearLayout) rootVew.findViewById(R.id.test7);
//        llTest8 = (LinearLayout) rootVew.findViewById(R.id.test8);

        rlClick.setOnClickListener(new MViewClick(0, context));

        llTest1.setOnClickListener(new MViewClick(1, context));
        llTest2.setOnClickListener(new MViewClick(2, context));
        llTest3.setOnClickListener(new MViewClick(3, context));
//        llTest4.setOnClickListener(new MViewClick(4, context));
//        llTest5.setOnClickListener(new MViewClick(5, context));
//        llTest6.setOnClickListener(new MViewClick(6, context));
//        llTest7.setOnClickListener(new MViewClick(7, context));
//        llTest8.setOnClickListener(new MViewClick(8, context));

    }

    /**
     * 点击事件
     */
    private class MViewClick implements View.OnClickListener {

        public int index;
        public Context context;

        public MViewClick(int index, Context context) {
            this.index = index;
            this.context = context;
        }

        @Override
        public void onClick(View v) {
            if (index == 0) {
                //加号按钮点击之后的执行
                _rlClickAction();
            } else {
                //showToast(context, "index=" + index);
                switch (index){
                    case 1:
                        Intent intent = new Intent(context, MainActivity.class);
                        context.startActivity(intent);
                        _close();
                        break;
                    case 2:
                        Intent intent1 = new Intent(context, MainActivity.class);
                        context.startActivity(intent1);
                        _close();
                        break;
                    case 3:
//                        Intent intent2 = new Intent(context, MainActivity.class);
//                        intent2.putExtra("type",1);
//                        context.startActivity(intent2);
//                        _close();
                        OkHttpUtils.post().url("http://tw.chinacloudapp.cn:8001/feedback_star/api/images")
                                .addHeader("Authorization",MainActivity.token)
                                .addFile("file-1","file1", new File(Environment.getExternalStorageDirectory() + "/DCIM/Camera/IMG_20161126_210304.jpg"))
                                .build().execute(new StringCallback() {
                            @Override
                            public void onError(Call call, Exception e, int id) {
                                Log.e(TAG, "onError: "+e.toString());
                                AppToast.makeShortToast(context,"Post Error...");
                            }

                            @Override
                            public void onResponse(String response, int id) {
                                Log.e(TAG, "onResponse: "+response);
                                AppToast.makeShortToast(context,"Bingo!!!");
                            }
                        });
                        break;
                }
            }
        }
    }

    Toast toast = null;

    /**
     * 防止toast 多次被创建
     *
     * @param context context
     * @param str     str
     */
    private void showToast(Context context, String str) {
        if (toast == null) {
            toast = Toast.makeText(context, str, Toast.LENGTH_SHORT);
        } else {
            toast.setText(str);
        }
        toast.show();
    }

    /**
     * 刚打开popupWindow 执行的动画
     */
    private void _openPopupWindowAction() {
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(ivBtn, "rotation", 0f, 135f);
        objectAnimator.setDuration(200);
        objectAnimator.start();

        _startAnimation(llTest1, 500, animatorProperty);
        _startAnimation(llTest2, 430, animatorProperty);
        _startAnimation(llTest3, 430, animatorProperty);
//        _startAnimation(llTest4, 500, animatorProperty);
//
//        _startAnimation(llTest5, 500, animatorProperty);
//        _startAnimation(llTest6, 430, animatorProperty);
//        _startAnimation(llTest7, 430, animatorProperty);
//        _startAnimation(llTest8, 500, animatorProperty);
    }


    /**
     * 关闭 popupWindow执行的动画
     */
    public void _rlClickAction() {
        if (ivBtn != null && rlClick != null) {

            ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(ivBtn, "rotation", 135f, 0f);
            objectAnimator.setDuration(300);
            objectAnimator.start();

            _closeAnimation(llTest1, 300, top);
            _closeAnimation(llTest2, 200, top);
            _closeAnimation(llTest3, 200, top);
//            _closeAnimation(llTest4, 300, top);
//            _closeAnimation(llTest5, 300, bottom);
//            _closeAnimation(llTest6, 200, bottom);
//            _closeAnimation(llTest7, 200, bottom);
//            _closeAnimation(llTest8, 300, bottom);

            rlClick.postDelayed(new Runnable() {
                @Override
                public void run() {
                    _close();
                }
            }, 300);

        }
    }


    /**
     * 弹起 popupWindow
     *
     * @param context context
     * @param parent  parent
     */
    public void _show(Context context, View parent) {
        _createView(context);
        if (popupWindow != null && !popupWindow.isShowing()) {
            popupWindow.showAtLocation(parent, Gravity.NO_GRAVITY, 0, 0);
            _openPopupWindowAction();
        }
    }

    /**
     * 关闭popupWindow
     */

    public void _close() {
        if (popupWindow != null && popupWindow.isShowing()) {
            popupWindow.dismiss();
            popupWindow = null;
        }
    }

    /**
     * @return popupWindow 是否显示了
     */
    public boolean _isShowing() {
        if (popupWindow == null) {
            return false;
        } else {
            return popupWindow.isShowing();
        }
    }

    /**
     * 关闭 popupWindow 时的动画
     *
     * @param view     mView
     * @param duration 动画执行时长
     * @param next     平移量
     */
    private void _closeAnimation(View view, int duration, int next) {
        ObjectAnimator anim = ObjectAnimator.ofFloat(view, "translationY", 0f, next);
        anim.setDuration(duration);
        anim.start();
    }

    /**
     * 启动动画
     *
     * @param view     view
     * @param duration 执行时长
     * @param distance 执行的轨迹数组
     */
    private void _startAnimation(View view, int duration, float[] distance) {
        ObjectAnimator anim = ObjectAnimator.ofFloat(view, "translationY", distance);
        anim.setDuration(duration);
        anim.start();
    }

}
