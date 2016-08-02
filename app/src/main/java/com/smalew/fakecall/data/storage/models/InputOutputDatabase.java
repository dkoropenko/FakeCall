package com.smalew.fakecall.data.storage.models;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.smalew.fakecall.utils.Constants;
import com.smalew.fakecall.utils.FakeCallApplication;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by koropenkods on 28.07.16.
 * Class for getting and setting info in database;
 */
public class InputOutputDatabase {
    private SQLiteDatabase mDatabase;
    private Template mTemplate;

    public InputOutputDatabase() {
    }

    public void insertTemplate(Template template) {
        openDB();

        ContentValues values = new ContentValues();
        values.put(Constants.DB_TEMPLATE_NAME, template.getTemplateName());
        values.put(Constants.DB_SUBSCRIBER_NAME, template.getSubscribeName());
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
                        Constants.DB_SUBSCRIBER_NAME,
                        Constants.DB_PHONE_NUMBER,
                        Constants.DB_MUSIC,
                        Constants.DB_AVATAR,
                        Constants.DB_VOICE},
                null, null, null, null, null);

        if (cursor != null){
            if (cursor.moveToFirst()){
                do{
                    mTemplate = new Template(cursor.getString(cursor.getColumnIndex(Constants.DB_TEMPLATE_NAME)),
                            cursor.getString(cursor.getColumnIndex(Constants.DB_SUBSCRIBER_NAME)),
                            cursor.getString(cursor.getColumnIndex(Constants.DB_PHONE_NUMBER)),
                            cursor.getString(cursor.getColumnIndex(Constants.DB_MUSIC)),
                            cursor.getString(cursor.getColumnIndex(Constants.DB_AVATAR)),
                            cursor.getString(cursor.getColumnIndex(Constants.DB_VOICE)));
                    result.add(mTemplate);
                }
                while (cursor.moveToNext());
            }
        }
        closeDB();

        return result;
    }

    public void deleteTemplate(Template template){
        openDB();
        mDatabase.delete(Constants.DB_NAME, Constants.DB_TEMPLATE_NAME +" = ?", new String[]{template.getTemplateName()});
        closeDB();
    }

    public void updateTemplate(String oldName, Template template){
        openDB();
        ContentValues values = new ContentValues();

        values.put(Constants.DB_TEMPLATE_NAME, template.getTemplateName());
        values.put(Constants.DB_SUBSCRIBER_NAME, template.getSubscribeName());
        values.put(Constants.DB_PHONE_NUMBER, template.getPhoneNumber());
        values.put(Constants.DB_MUSIC, template.getMusic());
        values.put(Constants.DB_AVATAR, template.getAvatar());
        values.put(Constants.DB_VOICE, template.getVoice());

        mDatabase.update(Constants.DB_NAME, values, Constants.DB_TEMPLATE_NAME +" = ?",
                new String[]{oldName});
    }

    private void openDB() {
        mDatabase = FakeCallApplication.getDatabase();
    }

    public void closeDB() {
        mDatabase.close();
    }
}
