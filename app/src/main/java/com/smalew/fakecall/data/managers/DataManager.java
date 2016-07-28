package com.smalew.fakecall.data.managers;

import com.smalew.fakecall.data.storage.models.Template;
import com.smalew.fakecall.data.storage.models.TemplatesInfo;

import java.util.List;

/**
 * Created by koropenkods on 28.07.16.
 */
public class DataManager {

    private static DataManager instance = null;
    private static TemplatesInfo sTemplatesInfo;


    public DataManager() {
        sTemplatesInfo = new TemplatesInfo();
    }

    public static DataManager getInstance() {
        if (instance == null) {
            instance = new DataManager();
        }

        return instance;
    }

    //======Region DataBase==========
    public List getTemplates() {
        return sTemplatesInfo.getTemplates();
    }

    public void setTemplate(Template template) {
        sTemplatesInfo.insertTemplate(template);
    }
    //======End Database==============

}
