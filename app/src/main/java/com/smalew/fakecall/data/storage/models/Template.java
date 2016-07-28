package com.smalew.fakecall.data.storage.models;

/**
 * Created by koropenkods on 28.07.16.
 * Class contains information about template from database.
 */
public class Template {

    private String mTemplatename,
            mSubscribeName,
            mPhoneNumber,
            mMusic,
            mAvatar,
            mVoice;

    public Template(String templatename, String subscribeName, String phoneNumber, String music, String avatar, String voice) {
        mTemplatename = templatename;
        mSubscribeName = subscribeName;
        mPhoneNumber = phoneNumber;
        mMusic = music;
        mAvatar = avatar;
        mVoice = voice;
    }

    public String getTemplatename() {
        return mTemplatename;
    }

    public void setTemplatename(String templatename) {
        mTemplatename = templatename;
    }

    public String getSubscribeName() {
        return mSubscribeName;
    }

    public void setSubscribeName(String subscribeName) {
        mSubscribeName = subscribeName;
    }

    public String getPhoneNumber() {
        return mPhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        mPhoneNumber = phoneNumber;
    }

    public String getMusic() {
        return mMusic;
    }

    public void setMusic(String music) {
        mMusic = music;
    }

    public String getAvatar() {
        return mAvatar;
    }

    public void setAvatar(String avatar) {
        mAvatar = avatar;
    }

    public String getVoice() {
        return mVoice;
    }

    public void setVoice(String voice) {
        mVoice = voice;
    }
}
