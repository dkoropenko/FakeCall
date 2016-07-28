package com.smalew.fakecall.utils;

import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by koropenkods on 28.07.16.
 */
public class FakeCallApplication extends Application {
    private final String TAG = "FakeCallApplication";

    private static Context sContext;
    private static SQLiteDatabase sDatabase;
    private static FakeCallDatabaseOpenHelper mHelper;

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = this;

        mHelper = new FakeCallDatabaseOpenHelper(this);
    }

    public static Context getContext() {
        return sContext;
    }

    public static SQLiteDatabase getDatabase() {
        sDatabase = mHelper.getWritableDatabase();
        return sDatabase;
    }
}
