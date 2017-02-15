package com.example.administrator.secondapplication;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.administrator.secondapplication.myclass.ClearEditText;
import com.example.administrator.secondapplication.myclass.CustomProgress;
import com.example.administrator.secondapplication.utils.PathUrlUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;

public class RewritePasswordActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView ivpassword;
    private Button butnext;
    private CheckBox checkeye;
    private Dialog dialog;
    public static ClearEditText etpassword;
    private static final String TAG = "RewritePasswordActivity";
    private String email,code;
    public static String access_token;
    public final String format = "^([A-Za-z]|[0-9])+$";//密码格式
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rewrite_password);
        initStatusBar();
        initview();
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
    private void initview() {
        ivpassword = (ImageView) findViewById(R.id.iv_back_repassword);
        ivpassword.setOnClickListener(this);
        butnext = (Button) findViewById(R.id.but_repassword_next);
        butnext.setOnClickListener(this);
        etpassword = (ClearEditText) findViewById(R.id.et_password);
        checkeye = (CheckBox) findViewById(R.id.check_eye);
        initCheckListener();
    }

    private void initCheckListener() {
        etpassword.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                return(keyEvent.getKeyCode() == KeyEvent.KEYCODE_ENTER);
            }
        });
        etpassword.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if(i==keyEvent.KEYCODE_ENTER){
                    Log.e(TAG, "onKey: ");
                    getNext();
                }
                return false;
            }
        });


        checkeye.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    etpassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }else{
                    etpassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
                etpassword.postInvalidate();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_back_repassword:
                finish();
                break;
            case R.id.but_repassword_next:
                getNext();
                break;

        }
    }

    private void getNext() {

        if(etpassword.getText().toString().length()>=6 &&etpassword.getText().toString().length()<=20) {
            dialog= CustomProgress.show(this,"Sending...",true,null);
            Intent intent = getIntent();
            email = RegisterActivity.email;
            code = intent.getStringExtra("code");
            OkHttpUtils
                    .put()//
                    .url(PathUrlUtils.UrlHead + PathUrlUtils.REGISTERPATH)
                    .requestBody(RequestBody.create(MediaType.parse("application/json; charset=utf-8"), "{\"email\": \"" + email + "\", \"validation_code\": \"" + "111111" + "\", \"password\": \"" + etpassword.getText().toString() + "\"}"))
                    .build()//
                    .execute(new MyStringCallback());
            Log.e(TAG, "onClick: " + email + ">>>>>>>>>" + 111111 + ">>>>>>>>>>>>" + etpassword.getText().toString());
        }else{
            Toast toast = Toast.makeText(RewritePasswordActivity.this, "between 6-20 characters,include letters and numbers", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER,0,0);
            toast.show();
        }
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
            dialog.dismiss();
            e.printStackTrace();
            Log.i(TAG, "onError: >>>>>>>>>>>>>>>>");
            //mTv.setText("onError:" + e.getMessage());
            Toast toast = Toast.makeText(RewritePasswordActivity.this, "between 6-20 characters,include letters and numbers", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER,0,0);
            toast.show();
        }

        @Override
        public void onResponse(String response, int id)
        {
            dialog.dismiss();
            Log.i(TAG, "onResponse：>>>>>>>>>>>>>>>>>>>>>>>>>>>>>complete"+response);
            // mTv.setText("onResponse:" + response);
            try {
                JSONObject jsonObject = new JSONObject(response);
                access_token=jsonObject.getString("access_token");
                Log.e(TAG, "onResponse:access_tokenaccess_tokenaccess_tokenaccess_tokenaccess_token "+access_token);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            Intent intent1 = new Intent(RewritePasswordActivity.this,AddBasicActivity.class);
            intent1.putExtra("password",etpassword.getText().toString());
            intent1.putExtra("access_token",access_token);
            startActivity(intent1);
            overridePendingTransition(R.anim.anim_enter,R.anim.anim_exit);
        }

    }

}
