package com.smalew.fakecall.utils;

import android.content.ContentValues;
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
        sqLiteDatabase.execSQL("create table "+ Constants.DB_NAME +" (_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                Constants.DB_TEMPLATE_NAME +" TEXT NOT NULL," +
                Constants.DB_SUBSCRIBER_NAME +" TEXT, " +
                Constants.DB_PHONE_NUMBER +" TEXT, " +
                Constants.DB_MUSIC +" TEXT," +
                Constants.DB_AVATAR +" TEXT," +
                Constants.DB_VOICE +" TEXT)");

        ContentValues values = new ContentValues();
        values.put(Constants.DB_TEMPLATE_NAME, "Полиция");
        values.put(Constants.DB_SUBSCRIBER_NAME, "Полиция");
        values.put(Constants.DB_PHONE_NUMBER, "02");
        values.put(Constants.DB_MUSIC, "Default");
        values.put(Constants.DB_AVATAR, "Default");
        values.put(Constants.DB_VOICE, "Default");

        sqLiteDatabase.insert(Constants.DB_NAME, null, values);

        values.put(Constants.DB_TEMPLATE_NAME, "Босс");
        values.put(Constants.DB_SUBSCRIBER_NAME, "Виктор Анатольевич");
        values.put(Constants.DB_PHONE_NUMBER, "8900 305 40-50");
        values.put(Constants.DB_MUSIC, "Boss.mp3");
        values.put(Constants.DB_AVATAR, "Boss.jpg");
        values.put(Constants.DB_VOICE, "BossVoice.mp3");

        // TODO: 28.07.16 Обдумать заполнение БД. Покурить мануалы как это делается.

        sqLiteDatabase.insert(Constants.DB_NAME, null, values);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //Do not changed database version yet.
    }
}
