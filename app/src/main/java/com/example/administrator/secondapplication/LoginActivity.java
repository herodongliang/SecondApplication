package com.example.administrator.secondapplication;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.secondapplication.myclass.CustomProgress;
import com.example.administrator.secondapplication.utils.EnCodeUtils;
import com.example.administrator.secondapplication.utils.PathUrlUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.Call;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView ivpassword;
    private Button butnext;
    private TextView tvforget,tvemail;
    private EditText etpsw;
    private static final String TAG = "LoginActivity";
    public static String access_token;
    private CheckBox check_eye_writepassword;
    private EditText et_password;
    private InputMethodManager imm;
    private Dialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initStatusBar();
        imm= (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        initview();
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            initkeyboard();
        }
        return super.onTouchEvent(event);
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
    private void initkeyboard() {
        if (this.getCurrentFocus() != null) {
            if (this.getCurrentFocus().getWindowToken() != null) {
                imm.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
    }
    private void initview() {
        check_eye_writepassword = (CheckBox) findViewById(R.id.check_eye_writepassword);
        initCheckListener();
        et_password = (EditText) findViewById(R.id.et_password);
        ivpassword = (ImageView) findViewById(R.id.iv_back_password);
        ivpassword.setOnClickListener(this);
        butnext = (Button) findViewById(R.id.but_writepassword_next);
        butnext.setOnClickListener(this);
        tvforget = (TextView) findViewById(R.id.tv_forget);
        tvforget.setOnClickListener(this);
        etpsw = (EditText) findViewById(R.id.et_password);
        tvemail=(TextView) findViewById(R.id.tv_textemail);
        tvemail.setText(RegisterActivity.email);

    }
    private void initCheckListener() {
        check_eye_writepassword.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    et_password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }else{
                    et_password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
                et_password.postInvalidate();
            }
        });
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_back_password:
                finish();
                overridePendingTransition(R.anim.back_enter,R.anim.back_exit);
                break;
            case R.id.but_writepassword_next:
                dialog= CustomProgress.show(this,"Sending...",true,null);
                initkeyboard();
                Log.e(TAG, "EEEEEEEEEEEEEEEEEEEEEEEEEEEEEE: "+tvemail.getText()+":"+etpsw.getText());
                byte[] bytes = EnCodeUtils.base64Encode(tvemail.getText()+":"+etpsw.getText());
                String s = "Basic "+new String(bytes);
                OkHttpUtils.get().url(PathUrlUtils.UrlHead+PathUrlUtils.ACCESSTOKEN)
                        .addHeader("Authorization",s)
                        .build().execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e(TAG, "onError: EEEEEEEEEEEEEEEEEEEEEEEEEEEEEE"+e.toString());
                        dialog.dismiss();
                        etpsw.setText("");
                        Toast toast = Toast.makeText(LoginActivity.this, "password error", Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.CENTER,0,0);
                        toast.show();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        dialog.dismiss();
                        Log.e(TAG, "onResponse: >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+response);
                        try {

                            JSONObject jsonObject=new JSONObject(response);
                            access_token=jsonObject.getString("access_token");
                            // 获取SharedPreferences对象
                            SharedPreferences sp = getSharedPreferences("token", MODE_PRIVATE);
                            // 获取Editor对象
                            SharedPreferences.Editor editor = sp.edit();
                            editor.putString("token", access_token);
                            editor.putString("email",tvemail.getText().toString());
                            editor.commit();
                          //  Log.e(TAG, "onResponse: ssssssssssssssssssssssssssssss"+sp.getString("token",""));
                            Toast.makeText(LoginActivity.this, "login successfully", Toast.LENGTH_SHORT).show();
                          //  Log.e(TAG, "onResponse: LLLLLLLLLLLLLLLLLLLLLLLLLLLL"+access_token);
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            intent.putExtra("accesstoken",access_token);
                            intent.putExtra("useremail",tvemail.getText().toString());
                            startActivity(intent);
                            overridePendingTransition(R.anim.anim_enter, R.anim.anim_exit);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });

                break;
            case R.id.tv_forget:
                Intent intent1 = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent1);
                overridePendingTransition(R.anim.anim_enter,R.anim.anim_exit);
                break;

        }
    }



}
