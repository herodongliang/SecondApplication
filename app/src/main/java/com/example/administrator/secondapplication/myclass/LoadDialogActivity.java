package com.example.administrator.secondapplication.myclass;

/**
 * Created by Administrator on 2016/12/20 0020.
 */

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.widget.EditText;
import android.widget.TextView;

public class LoadDialogActivity extends AppCompatActivity {
    private ProgressDialog processDialog;
    private EditText et;
    private TextView text;


    public void showResult(final String message) {

        LoadDialogActivity.this.runOnUiThread(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                // show
                AlertDialog.Builder builder = new AlertDialog.Builder(LoadDialogActivity.this);
                TextView tv = new TextView(LoadDialogActivity.this);
                tv.setTextSize(20);
                tv.setGravity(Gravity.CENTER_HORIZONTAL);
                tv.setText(message);
                builder.setView(tv);
                builder.setPositiveButton("确定", null);
                builder.create().show();
            }
        });
    }

    public void showResult(final String title, final String message) {

        LoadDialogActivity.this.runOnUiThread(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                // show
                AlertDialog.Builder builder = new AlertDialog.Builder(LoadDialogActivity.this);
                TextView tv = new TextView(LoadDialogActivity.this);
                tv.setTextSize(20);
                tv.setGravity(Gravity.CENTER_HORIZONTAL);
                tv.setText(message);
                builder.setTitle(title);
                builder.setView(tv);
                builder.setPositiveButton("确定", null);
                builder.create().show();
            }
        });
    }

    public void showWait(final String message) {
        LoadDialogActivity.this.runOnUiThread(new Runnable() {

            @Override
            public void run() {
                processDialog = new ProgressDialog(LoadDialogActivity.this);
                processDialog.setMessage(message);
                processDialog.setIndeterminate(true);
                processDialog.setCancelable(false);
                processDialog.show();

            }
        });

    }


    public void waitClose() {
        LoadDialogActivity.this.runOnUiThread(new Runnable() {

            @Override
            public void run() {
                if (processDialog!=null&&processDialog.isShowing()) {
                    processDialog.dismiss();
                }
            }
        });

    }



}
