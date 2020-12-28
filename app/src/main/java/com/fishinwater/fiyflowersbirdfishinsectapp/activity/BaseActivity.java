package com.fishinwater.fiyflowersbirdfishinsectapp.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity {
    private final String TAG = BaseActivity.class.getName();
    private Toast toast = null;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

//    /**
//     * @param context 上下文对象
//     * @param start   其实activity
//     * @param target  目标activity
//     */
//    public void jumpToActivity(Context context, Class start, Class target) {
//        Intent intent = new Intent(context, target);
//        //------用户返回上一级activity
//        if (target == CarFindingActivity.class) {
//            intent.putExtra("activity", start);
//        }
//        context.startActivity(intent);
//        finish();
//    }

    public void jumpToActivity(Context context, Class target) {
        Intent intent = new Intent(context, target);
        //------用户返回上一级activity
        context.startActivity(intent);
        finish();
    }


    /**
     * @param context
     * @param text
     */
    public void showToastLong(Context context, String text) {
        if (toast == null) {
            toast = Toast.makeText(context, text, Toast.LENGTH_LONG);
        } else {
            toast.setText(text);
        }
        toast.show();
    }

    public void showToastLong(String text) {
        if (toast == null) {
            toast = Toast.makeText(BaseActivity.this, text, Toast.LENGTH_LONG);
        } else {
            toast.setText(text);
        }
        toast.show();
    }

    /**
     * @param context
     * @param text
     */
    public void showToastShort(Context context, String text) {
        if (toast == null) {
            toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
        } else {
            toast.setText(text);
        }
        toast.show();
    }

    public void showToastShort(String text) {
        if (toast == null) {
            toast = Toast.makeText(BaseActivity.this, text, Toast.LENGTH_SHORT);
        } else {
            toast.setText(text);
        }
        toast.show();
    }


    //-----显示ProgressDialog
    public void showProgress(String message) {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(BaseActivity.this, ProgressDialog.STYLE_SPINNER);
            progressDialog.setCancelable(false);//设置点击不消失
        }
        if (progressDialog.isShowing()) {
            progressDialog.setMessage(message);
        } else {
            progressDialog.setMessage(message);
            progressDialog.show();
        }
    }
    //------取消ProgressDialog
    public void removeProgress(){
        if (progressDialog==null){
            return;
        }
        if (progressDialog.isShowing()){
            progressDialog.dismiss();
        }

    }



    /**
     *
     */
    @Override
    protected void onStart() {
        super.onStart();

        Log.d(TAG, "onStart: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: ");
    }

    @Override
    protected void onStop() {
        removeProgress();//让progressdialog 消失
        super.onStop();
        Log.d(TAG, "onStop: ");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }


}