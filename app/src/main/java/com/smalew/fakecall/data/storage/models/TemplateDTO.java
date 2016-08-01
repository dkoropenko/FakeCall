package com.smalew.fakecall.data.storage.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by koropenkods on 29.07.16.
 */
public class TemplateDTO implements Parcelable {

    private String mTemplatename;
    private String mSubscribeName;
    private String mPhoneNumber;
    private String mMusic;
    private String mAvatar;
    private String mVoice;

    public TemplateDTO(Template template) {
        mTemplatename = template.getTemplatename();
        mSubscribeName = template.getSubscribeName();
        mPhoneNumber = template.getPhoneNumber();
        mMusic = template.getMusic();
        mAvatar = template.getAvatar();
        mVoice = template.getVoice();
    }

    protected TemplateDTO(Parcel in) {
        mTemplatename = in.readString();
        mSubscribeName = in.readString();
        mPhoneNumber = in.readString();
        mMusic = in.readString();
        mAvatar = in.readString();
        mVoice = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mTemplatename);
        dest.writeString(mSubscribeName);
        dest.writeString(mPhoneNumber);
        dest.writeString(mMusic);
        dest.writeString(mAvatar);
        dest.writeString(mVoice);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<TemplateDTO> CREATOR = new Parcelable.Creator<TemplateDTO>() {
        @Override
        public TemplateDTO createFromParcel(Parcel in) {
            return new TemplateDTO(in);
        }

        @Override
        public TemplateDTO[] newArray(int size) {
            return new TemplateDTO[size];
        }
    };

    public String getTemplateName() {
        return mTemplatename;
    }

    public String getSubscribeName() {
        return mSubscribeName;
    }

    public String getPhoneNumber() { return mPhoneNumber; }

    public String getMusic() {
        return mMusic;
    }

    public String getAvatar() {
        return mAvatar;
    }

    public String getVoice() {
        return mVoice;
    }
}
