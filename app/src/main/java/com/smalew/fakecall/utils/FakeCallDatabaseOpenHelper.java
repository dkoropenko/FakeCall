package com.smalew.fakecall.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by koropenkods on 28.07.16.
 */
public class FakeCallDatabaseOpenHelper extends SQLiteOpenHelper {

    public FakeCallDatabaseOpenHelper(Context context) {
        super(context, Constants.DB_NAME, null, Constants.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table "+ Constants.DB_NAME +" (_id INTEGER NOTNULL AUTOINCREMENT, " +
                Constants.DB_TEMPLATE_NAME +" TEXT NOTNULL," +
                Constants.DB_SUBSCIBER_NAME +" TEXT, " +
                Constants.DB_PHONE_NUMBER +" TEXT, " +
                Constants.DB_MUSIC +" TEXT," +
                Constants.DB_AVATAR +" TEXT," +
                Constants.DB_VOICE +" TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //Do not changed database version yet.
    }
}
