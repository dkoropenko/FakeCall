package com.smalew.fakecall.utils;

/**
 * Created by koropenkods on 28.07.16.
 */
public interface Constants {

    int DB_VERSION = 1;
    String DB_NAME = "fake_call_db";
    String DB_TEMPLATE_NAME = "template_name";
    String DB_SUBSCRIBER_NAME = "subscriber_name";
    String DB_PHONE_NUMBER = "phone_number";
    String DB_MUSIC = "music";
    String DB_AVATAR = "avatar";
    String DB_VOICE = "voice";

    String PARCABLE_VALUE = "parcableValue";
    String LIST_VALUES = "listValue";

    String ADD_NEW_TEMPLATE_FLAG = "newTemplateFlag";

    int DIALOG_MUSIC = 1;
    int DIALOG_AVATAR = 2;
    int DIALOG_VOICE = 3;
    int INTENT_MUSIC = 91;
    int INTENT_AVATAR = 92;
    int INTENT_VOICE = 93;
}
