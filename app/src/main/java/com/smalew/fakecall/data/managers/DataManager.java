package com.smalew.fakecall.data.managers;

import android.content.Context;

import com.smalew.fakecall.data.storage.models.Template;
import com.smalew.fakecall.data.storage.models.InputOutputDatabase;
import com.smalew.fakecall.utils.FakeCallApplication;

import java.util.List;

/**
 * Created by koropenkods on 28.07.16.
 */
public class DataManager {

    private static DataManager instance = null;
    private static InputOutputDatabase sInputOutputDatabase;


    public DataManager() {
        sInputOutputDatabase = new InputOutputDatabase();
    }

    public static DataManager getInstance() {
        if (instance == null) {
            instance = new DataManager();
        }
        return instance;
    }

    public Context getContext(){
        return FakeCallApplication.getContext();
    }

    //======Region DataBase==========
    public List getTemplates() {
        return sInputOutputDatabase.getTemplates();
    }
    public void setTemplate(Template template) {
        sInputOutputDatabase.insertTemplate(template);
    }
    public void deleteTemplate(Template template) { sInputOutputDatabase.deleteTemplate(template);}
    public void updateTemplate(String oldName, Template template) { sInputOutputDatabase.updateTemplate(oldName, template);}
    //======End Database==============

}
