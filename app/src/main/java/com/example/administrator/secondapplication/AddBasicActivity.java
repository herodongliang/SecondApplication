package com.example.administrator.secondapplication;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;


import com.example.administrator.secondapplication.myclass.CustomProgress;
import com.example.administrator.secondapplication.myclass.LoadDialogActivity;
import com.example.administrator.secondapplication.utils.EnCodeUtils;
import com.example.administrator.secondapplication.utils.PathUrlUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class AddBasicActivity extends Activity implements View.OnClickListener{

    private ImageView iv_back;
    private EditText et_country,et_firstname,et_lastname,et_department;
    private Button but_next;
    private LinearLayout ll_profilephoto;
    ArrayList<String> selectedPhotos = new ArrayList<>();
    private Dialog dialog;
    private static final String TAG = "AddBasicActivity";
    private String getpassword;
    private String email;
    private String url,s;
    private String token,access_token;
    private InputMethodManager imm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_basic);
        initStatusBar();
        getpassword = getIntent().getStringExtra("password");
        email = RegisterActivity.email;
        byte[] bytes = EnCodeUtils.base64Encode(email+":"+getpassword);
        s = "Basic "+new String(bytes);
        imm= (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
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


        ll_profilephoto = (LinearLayout) findViewById(R.id.ll_profile_photo);
        ll_profilephoto.setOnClickListener(this);
        iv_back = (ImageView) findViewById(R.id.iv_back_basic);
        et_country = (EditText) findViewById(R.id.et_basic_country);
        et_firstname = (EditText) findViewById(R.id.et_basic_firstname);
        et_lastname = (EditText) findViewById(R.id.et_basic_lastname);
        et_department = (EditText) findViewById(R.id.et_basic_department);
        et_country.setSelection(et_country.getText().length());
        et_firstname.setSelection(et_firstname.getText().length());
        et_lastname.setSelection(et_lastname.getText().length());
        et_department.setSelection(et_department.getText().length());
        but_next = (Button) findViewById(R.id.but_basic_next);
        iv_back.setOnClickListener(this);
        but_next.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_back_basic:
                finish();
                overridePendingTransition(R.anim.back_enter,R.anim.back_exit);
                break;
            case R.id.but_basic_next:
                Log.e(TAG, s+ "onClick: "+"{\"first_name\": \"" + et_firstname.getText() + "\", \"last_name\": \"" + et_lastname.getText() + "\", \"country\": \"" + et_country.getText() + "\", \"department\": \"" + et_department.getText() + "\", \"avatar\": \"" + "http://testurl/image.png" + "\"}" );
                if(et_country.getText().length()>0) {
                    if(et_firstname.getText().length()>0 ) {
                        if(et_lastname.getText().length()>0) {
                            if(et_department.getText().length()>0) {
                                initkeyboard();//收起键盘
                                dialog= CustomProgress.show(this,"Sending...",true,null);
                                OkHttpUtils.get().url(PathUrlUtils.UrlHead + PathUrlUtils.ACCESSTOKEN)
                                        .addHeader("Authorization", s)
                                        .build().execute(new StringCallback() {
                                    @Override
                                    public void onError(Call call, Exception e, int id) {
                                        dialog.dismiss();
                                        Log.e(TAG, "onError: EEEEEEEEEEEEEEEEEEEEEEEEEEEEEE" + e.toString());
                                        //Toast.makeText(AddBasicActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                    }

                                    @Override
                                    public void onResponse(String response, int id) {
                                        dialog.dismiss();
                                        Log.e(TAG, "onResponse: >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + response);
                                        try {
                                            JSONObject jsonObject = new JSONObject(response);
                                            access_token = jsonObject.getString("access_token");
                                            byte[] bytes = EnCodeUtils.base64Encode(email + ":" + access_token);
                                            Log.e(TAG, "getIntenttoken: " + email + access_token);
                                            token = "Basic " + new String(bytes);

                                            OkHttpUtils.put()
                                                    .url("http://tw.chinacloudapp.cn:8000/one_auth/api/user/profile")
                                                    .addHeader("Authorization", token)
                                                    .requestBody(RequestBody.create(MediaType.parse("application/json; charset=utf-8"), "{\"first_name\": \"" + et_firstname.getText() + "\", \"last_name\": \"" + et_lastname.getText() + "\", \"country\": \"" + et_country.getText() + "\", \"department\": \"" + et_department.getText() + "\", \"avatar\": \"" + "http://img2.imgtn.bdimg.com/it/u=2452097355,3155741241&fm=21&gp=0.jpg" + "\"}"))
                                                    .build()
                                                    .execute(new StringCallback() {
                                                        @Override
                                                        public void onError(Call call, Exception e, int id) {
                                                            Log.e(TAG, "onError: " + e.toString());
                                                            dialog.dismiss();
                                                            Toast.makeText(AddBasicActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                                        }

                                                        @Override
                                                        public void onResponse(String response, int id) {
                                                            Log.e(TAG, "onResponse: " + response);
                                                            try {
                                                                JSONObject jsonobject2 = new JSONObject(response);
                                                                String seconedtoken = jsonobject2.getString("access_token");
                                                                dialog.dismiss();
                                                                // 获取SharedPreferences对象
                                                                SharedPreferences sp = getSharedPreferences("token", MODE_PRIVATE);
                                                                // 获取Editor对象
                                                                SharedPreferences.Editor editor = sp.edit();
                                                                editor.putString("token", seconedtoken);
                                                                editor.putString("email", email);
                                                                editor.commit();
                                                                Toast.makeText(AddBasicActivity.this, "login successfully", Toast.LENGTH_SHORT).show();
                                                                Intent intent = new Intent(AddBasicActivity.this, MainActivity.class);
                                                                startActivity(intent);
                                                            } catch (JSONException e) {
                                                                e.printStackTrace();
                                                            }

                                                        }
                                                    });

                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                });
                            }else{
                                Toast toast = Toast.makeText(AddBasicActivity.this, "Please entry Department", Toast.LENGTH_SHORT);
                                toast.setGravity(Gravity.CENTER,0,0);
                                toast.show();
                            }
                        }
                        else{
                            Toast toast = Toast.makeText(AddBasicActivity.this, "Please entry Last Name", Toast.LENGTH_SHORT);
                            toast.setGravity(Gravity.CENTER,0,0);
                            toast.show();
                        }
                    }else{
                        Toast toast = Toast.makeText(AddBasicActivity.this, "Please entry First Name", Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.CENTER,0,0);
                        toast.show();
                    }

                }else{
                    Toast toast = Toast.makeText(AddBasicActivity.this, "Please entry Country", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER,0,0);
                    toast.show();
                }
                break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case 200:
                List<String> photos = null;
                if (resultCode == RESULT_OK ) {
                    if (data != null) {
                       // photos = data.getStringArrayListExtra(PhotoPickerActivity.KEY_SELECTED_PHOTOS);
                    }
                    selectedPhotos.clear();

                    if (photos != null) {
                        selectedPhotos.addAll(photos);
                    }
                    //addPicLayout.setPaths(selectedPhotos);
                    Uri uri = Uri.fromFile(new File(selectedPhotos.get(0)));
                    Log.e(TAG, "onActivityResult: "+uri.toString());

                    Log.e(TAG, new File(selectedPhotos.get(0))+"onActivityResult: "+uri.toString());

                    OkHttpUtils.postFile().url(PathUrlUtils.UrlHead + PathUrlUtils.USERPHOTO)
                            .addHeader("Authorization",s)
                            .file(new File(selectedPhotos.get(0)))
                            .build()
                            .connTimeOut(50000)
                            .execute(new StringCallback() {
                                @Override
                                public void onError(Call call, Exception e, int id) {
                                    Log.e(TAG, "onError: "+e.toString());
                                    Toast.makeText(AddBasicActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public void onResponse(String response, int id) {
                                    Log.e(TAG, "onResponse: "+response);
                                    try {
                                        JSONObject jsonobject = new JSONObject(response);
                                        url= jsonobject.getString("url");
                                        Log.e(TAG, "onResponse: "+url);
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            });
                }
                break;
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
        if (this.getCurrentFocus() != null) {
            if (this.getCurrentFocus().getWindowToken() != null) {
                imm.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
    }
}
