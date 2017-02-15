package com.example.administrator.secondapplication;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.secondapplication.myclass.ClearEditText;
import com.example.administrator.secondapplication.myclass.CountdownButton;
import com.example.administrator.secondapplication.myclass.CustomProgress;
import com.example.administrator.secondapplication.utils.PathUrlUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;


public class ExamineCodeActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView ivback;
    private Button but_next;
    private CountdownButton but_sendcode;
    private String getemail;
    private TextView textemail;
    private Dialog dialog;
    private static final String TAG = "ExamineCodeActivity";
    private ClearEditText et_code;
    private String etcode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_examine_code);
        initStatusBar();
        initview();
        getintentemail();

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
    private void getintentemail() {
        Intent intent = getIntent();
        getemail=intent.getStringExtra("etmail");
        textemail.setText(getemail);
    }

    private void initview() {
        ivback = (ImageView) findViewById(R.id.iv_back_examine);
        ivback.setOnClickListener(this);
        but_next = (Button) findViewById(R.id.but_examine_next);
        but_next.setOnClickListener(this);
        but_sendcode = (CountdownButton) findViewById(R.id.but_sendcode);
        but_sendcode.setAfterText("Resend");
        but_sendcode.setOnClickListener(this);
        textemail = (TextView) findViewById(R.id.text_intent_email);
        et_code = (ClearEditText) findViewById(R.id.et_code);

        et_code.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                return(keyEvent.getKeyCode() == KeyEvent.KEYCODE_ENTER);
            }
        });
        et_code.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if(i==keyEvent.KEYCODE_ENTER){
                    Log.e(TAG, "onKey: ");
                    getNext();
                }
                return false;
            }
        });

        etcode=et_code.getText().toString();
        //实例化SharedPreferences对象（第一步）
        SharedPreferences mySharedPreferences= getSharedPreferences("name",
                Activity.MODE_PRIVATE);
//实例化SharedPreferences.Editor对象（第二步）
        SharedPreferences.Editor editor = mySharedPreferences.edit();
//用putString的方法保存数据
        editor.putString("username",etcode);
//提交当前数据
        editor.commit();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_back_examine:
                finish();
                overridePendingTransition(R.anim.back_enter,R.anim.back_exit);
                break;
            case R.id.but_examine_next:
                getNext();

                break;
            case R.id.but_sendcode:

//                OkHttpUtils.postString()
//                        .url(PathUrlUtils.UrlHead + PathUrlUtils.REGISTERPATH)
//                        .mediaType(MediaType.parse("application/json; charset=utf-8"))
//                        .content("{\"email\": \"" +
//                                textemail.getText().toString()+
//                                "\"}")
//                        //.content("{\"email\": \"heroddfsdfsd@163.com\"}")
//                        .build().execute(new MyStringCallback());

                break;

        }
    }

    private void getNext() {
        dialog= CustomProgress.show(this,"Sending...",true,null);
        OkHttpUtils
                .put()
                .url(PathUrlUtils.UrlHead+ PathUrlUtils.VALIDATION)
                .requestBody(RequestBody.create(MediaType.parse("application/json; charset=utf-8"),"{\"email\": \""+textemail.getText().toString()+"\", \"validation_code\": \""+et_code.getText().toString()+"\"}"))
                .build()
                .execute(new MyStringCallback());
    }

    private class MyStringCallback extends StringCallback
    {
        @Override
        public void onBefore(Request request, int id)
        {
            //setTitle("loading...");
            Log.i(TAG, "onBefore: >>>>>>>>>>>>>>>>");
        }

        @Override
        public void onAfter(int id)
        {
            //setTitle("Sample-okHttp");
            Log.i(TAG, "onAfter: >>>>>>>>>>>>>>>>>>>>>>");
        }

        @Override
        public void onError(Call call, Exception e, int id)
        {
            e.printStackTrace();
            Log.i(TAG, "onError: >>>>>>>>>>>>>>>>");
            //mTv.setText("onError:" + e.getMessage());
            dialog.dismiss();
            Toast toast = Toast.makeText(ExamineCodeActivity.this, "Invalid code", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER,0,0);
            toast.show();
        }

        @Override
        public void onResponse(String response, int id)
        {
            Log.i(TAG, "onResponse：>>>>>>>>>>>>>>>>>>>>>>>>>>>>>complete"+response);
            // mTv.setText("onResponse:" + response);
            dialog.dismiss();
            Intent intent = new Intent(ExamineCodeActivity.this,RewritePasswordActivity.class);
            intent.putExtra("email",getemail);
            intent.putExtra("code",etcode);
            Log.e(TAG, "onClick: REEEEEEEEEEEEEEEEEEEEEE"+etcode);
            startActivity(intent);
            overridePendingTransition(R.anim.anim_enter,R.anim.anim_exit);
        }

        @Override
        public void inProgress(float progress, long total, int id)
        {
            Log.e(TAG, "inProgress:" + progress);
            // mProgressBar.setProgress((int) (100 * progress));
        }
    }

}
