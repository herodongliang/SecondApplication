package com.example.administrator.secondapplication;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.secondapplication.myclass.ClearEditText;
import com.example.administrator.secondapplication.myclass.CustomProgress;
import com.example.administrator.secondapplication.myclass.LoadDialogActivity;
import com.example.administrator.secondapplication.utils.PathUrlUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.Request;


public class RegisterActivity extends Activity implements View.OnClickListener {

    private Button but_next;
    private InputMethodManager imm;
    private ClearEditText etemail;
    public static String email;
    private Dialog dialog;
    private static final String TAG = "RegisterActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initStatusBar();
        initview();
        imm= (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
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
        etemail = (ClearEditText) findViewById(R.id.et_email);
        but_next = (Button) findViewById(R.id.but_register_next);
        but_next.setOnClickListener(this);
        etemail.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                return(keyEvent.getKeyCode() == KeyEvent.KEYCODE_ENTER);
            }
        });
        etemail.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if(i==keyEvent.KEYCODE_ENTER){
                    Log.e(TAG, "onKey: ");
                    getNext();
                }
                return false;
            }
        });
        etemail.setText(getIntent().getStringExtra("email"));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.but_register_next:
                getNext();
                break;

        }
    }

    private void getNext() {
        email=etemail.getText().toString();
        if(email.matches("\\w+@\\w+\\.\\w+")) {
            dialog= CustomProgress.show(this,"Sending...",true,null);
            OkHttpUtils.postString()
                    .url(PathUrlUtils.UrlHead + PathUrlUtils.REGISTERPATH)
                    .mediaType(MediaType.parse("application/json; charset=utf-8"))
                    .content("{\"email\": \"" +
                            email+
                            "\"}")
                    //.content("{\"email\": \"heroddfsdfsd@163.com\"}")
                    .build().execute(new MyStringCallback());

        }else {
            Toast toast= Toast.makeText(RegisterActivity.this, "Please enter the correct email address.", Toast.LENGTH_SHORT);
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
            e.printStackTrace();
            Log.e(TAG, "onError: >>>>>>>>>>>>>>>>"+e.toString());
            dialog.dismiss();
            if(e.toString().equals("java.io.IOException: request failed , reponse's code is : 422")) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                intent.putExtra("email", etemail.getText().toString());//5a2641ji
                startActivity(intent);
            }else{
                Toast.makeText(RegisterActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onResponse(String response, int id)
        {
            dialog.dismiss();
            Log.e(TAG, "onResponse：>>>>>>>>>>>>>>>>>>>>>>>>>>>>>complete"+response);
            try {
                JSONObject jsonObject = new JSONObject(response);
                if(jsonObject!=null && jsonObject.length()==1) {
                    String messages = jsonObject.getString("message");
                    if (messages.equals("Please set your profile")) {
                        //Toast.makeText(RegisterActivity.this, "Update your profile please", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(RegisterActivity.this, ExamineCodeActivity.class);
                        intent.putExtra("etmail", email);
                        startActivity(intent);
                    }
                }else if(jsonObject!=null && jsonObject.length()==2){
                    String isdedeactivedemail = jsonObject.getString("status");
                    if(isdedeactivedemail.length()>0) {
                    Log.e(TAG, "onResponse: " + isdedeactivedemail);
                    if (isdedeactivedemail.equals("deactived")) {
                        Intent intent = new Intent(RegisterActivity.this, ExamineCodeActivity.class);
                        //String etemail=et_email.getText().toString();
                        intent.putExtra("etmail", email);
                        startActivity(intent);
                    }
                    }
                }


            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

        @Override
        public void inProgress(float progress, long total, int id)
        {
            Log.e(TAG, "inProgress:" + progress);
            // mProgressBar.setProgress((int) (100 * progress));
        }
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            initkeyboard();
        }
        return super.onTouchEvent(event);

    }

    private void initkeyboard() {
        if (RegisterActivity.this.getCurrentFocus() != null) {
            if (RegisterActivity.this.getCurrentFocus().getWindowToken() != null) {
                imm.hideSoftInputFromWindow(RegisterActivity.this.getCurrentFocus().getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
    }

}
