package com.smalew.fakecall.ui.activity;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

/**
 * Created by koropenkods on 28.07.16.
 */
public class BaseActivity extends AppCompatActivity {

    public void showToast(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
