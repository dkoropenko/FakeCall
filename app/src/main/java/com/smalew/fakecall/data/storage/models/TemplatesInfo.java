package com.smalew.fakecall.data.storage.models;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteDatabase;

import com.smalew.fakecall.utils.Constants;
import com.smalew.fakecall.utils.FakeCallApplication;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by koropenkods on 28.07.16.
 * Class for getting and setting info in database;
 */
public class TemplatesInfo {
    private SQLiteDatabase mDatabase;


    public TemplatesInfo() {
    }

    public void insertTemplate(Template template) {
        openDB();

        ContentValues values = new ContentValues();
        values.put(Constants.DB_TEMPLATE_NAME, template.getTemplatename());
        values.put(Constants.DB_SUBSCIBER_NAME, template.getSubscribeName());
        values.put(Constants.DB_PHONE_NUMBER, template.getPhoneNumber());
        values.put(Constants.DB_MUSIC, template.getMusic());
        values.put(Constants.DB_AVATAR, template.getAvatar());
        values.put(Constants.DB_VOICE, template.getVoice());

        mDatabase.insert(Constants.DB_NAME, null, values);
        closeDB();
    }

    public List<Template> getTemplates() {
        openDB();
        List<Template> result = new ArrayList<>();
        Cursor cursor = mDatabase.query(Constants.DB_NAME,
                new String[]{Constants.DB_TEMPLATE_NAME,
                        Constants.DB_SUBSCIBER_NAME,
                        Constants.DB_PHONE_NUMBER,
                        Constants.DB_MUSIC,
                        Constants.DB_AVATAR,
                        Constants.DB_VOICE},
                null, null, null, null, null);

        if (cursor != null){
            if (cursor.moveToFirst()){
                do{
                    // TODO: 28.07.16 Create method for getting info from Cursor
                    result.add(null);
                }
                while (cursor.moveToNext());
            }
        }

        return result;
    }

    private void openDB() {
        mDatabase = FakeCallApplication.getDatabase();
    }

    public void closeDB() {
        mDatabase.close();
    }
}
