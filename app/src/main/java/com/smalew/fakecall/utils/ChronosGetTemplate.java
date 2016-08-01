package com.smalew.fakecall.utils;

import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.redmadrobot.chronos.ChronosOperation;
import com.redmadrobot.chronos.ChronosOperationResult;
import com.smalew.fakecall.data.managers.DataManager;
import com.smalew.fakecall.data.storage.models.Template;
import com.smalew.fakecall.data.storage.models.TemplateDTO;

import java.util.List;

/**
 * Created by koropenkods on 01.08.16.
 */
public class ChronosGetTemplate extends ChronosOperation<List<Template>> {

    private DataManager mDataManager;

    @Nullable
    @Override
    public List<Template> run() {
        mDataManager = DataManager.getInstance();

        return mDataManager.getTemplates();
    }

    @NonNull
    @Override
    public Class<? extends ChronosOperationResult<List<Template>>> getResultClass() {
        return Result.class;
    }

    public final static class Result extends ChronosOperationResult<List<Template>>{

    }
}
