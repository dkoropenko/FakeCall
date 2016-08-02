package com.smalew.fakecall.utils;

import android.app.Dialog;
import android.content.DialogInterface;
import android.util.Log;

/**
 * Created by koropenkods on 02.08.16.
 */
public class DialogListener implements DialogInterface.OnClickListener {
    private static final String TAG = "DialogListener";

    @Override
    public void onClick(DialogInterface dialogInterface, int i) {
        Log.d(TAG, "onClick: "+ dialogInterface.toString());
        Log.d(TAG, "onClick: "+ i);

        // TODO: 02.08.16 Continue. Rewrite with fragmentDialog!!!
    }
}
