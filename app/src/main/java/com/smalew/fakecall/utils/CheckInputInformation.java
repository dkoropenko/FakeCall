package com.smalew.fakecall.utils;

import android.content.Context;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextWatcher;

import com.smalew.fakecall.R;
import com.smalew.fakecall.data.managers.DataManager;
import com.smalew.fakecall.data.storage.models.Template;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by koropenkods on 02.08.16.
 */
public class CheckInputInformation implements TextWatcher {

    private Context mContext;

    ArrayList<String> mTemplates;
    private TextInputLayout mTextInputLayout;

    private String mOldName;
    private String mError;
    private boolean mErrorFlag;

    public CheckInputInformation(TextInputLayout textInputLayout, ArrayList<String> templatesName, String oldName) {
        mContext = DataManager.getInstance().getContext();

        mTemplates = templatesName;
        mTextInputLayout = textInputLayout;

        mOldName = oldName;
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {
        if (mTemplates.contains(editable.toString())) {
            if (!editable.toString().equals(mOldName)){
                mErrorFlag = true;
                mError = mContext.getString(R.string.error_template_exist);
            }
        }
        if (editable.toString().isEmpty()){
            mErrorFlag = true;
            mError = mContext.getString(R.string.error_empty_name);
        }

        if (mErrorFlag){
            showError();
            mErrorFlag = false;
        }
        else {
            hideError();
        }
    }

    private void showError() {
        mTextInputLayout.setErrorEnabled(true);
        mTextInputLayout.setError(mError);
    }

    private void hideError() {
        mTextInputLayout.setError("");
        mTextInputLayout.setErrorEnabled(false);
    }
}
