package com.smalew.fakecall.ui.activity;

import android.content.Intent;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.smalew.fakecall.R;
import com.smalew.fakecall.data.managers.DataManager;
import com.smalew.fakecall.data.storage.models.Template;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CallActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "CallActivity";
    @BindView(R.id.incoming_call_abonent_name)
    TextView mSubscriberName;

    @BindView(R.id.incoming_call_phone_number)
    TextView mPhoneNumber;

    @BindView(R.id.incoming_call_avatar)
    ImageView mAvatar;

    @BindView(R.id.incoming_call_answer_btn)
    ImageButton mAnswerBtn;

    @BindView(R.id.incoming_call_drop_btn)
    ImageButton mDropBtn;

    private DataManager mDataManager;
    private List<Template> mTemplateList;
    private Template mTemplate;

    private SoundPool mSoundPool;
    private int musicId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call);
        ButterKnife.bind(this);

        mDataManager = DataManager.getInstance();
        mTemplateList = mDataManager.getTemplates();

        mTemplate = mTemplateList.get(1);

        initText();
        initPhoto();
        initBtn();
        //initMusic();
    }

    private void initText(){
        String name = "", phone = "";

        if (mTemplate.getSubscribeName().equals("")){
            name = "Неизвестный пользователь";
        }else{
            name = mTemplate.getSubscribeName();
        }

        if (mTemplate.getPhoneNumber().equals("")){
            phone = "Номер скрыт";
        }else{
            phone = mTemplate.getSubscribeName();
        }

        mSubscriberName.setText(name);
        mPhoneNumber.setText(phone);
    }

    private void initBtn(){
        mAnswerBtn.setOnClickListener(this);
        mDropBtn.setOnClickListener(this);
    }

    private void initPhoto(){
        // TODO: 03.08.16 Create with Picasso

        Picasso.with(this)
                .load(R.raw.boss)
                .placeholder(R.drawable.avatar)
                .error(R.drawable.avatar)
                .into(mAvatar);
    }

    private void initMusic(){
        final int MAX_STREAMS = 5;

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            AudioAttributes audioAttributes = new AudioAttributes.Builder().
                    setUsage(AudioAttributes.USAGE_MEDIA).
                    setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION).
                    build();
            mSoundPool = new SoundPool.Builder().
                    setAudioAttributes(audioAttributes).
                    setMaxStreams(MAX_STREAMS).
                    build();
        }else{
            mSoundPool = new SoundPool(MAX_STREAMS, AudioManager.STREAM_MUSIC, 0);
        }


        mSoundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool soundPool, int i, int i1) {
                mSoundPool.play(musicId, 1,1,0,0,1);
            }
        });

        musicId = mSoundPool.load(this, R.raw.phone_ring, 1);
        Log.d(TAG, "initMusic: musicId = "+ musicId);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.incoming_call_answer_btn:
                mSoundPool.stop(musicId);
                break;
            case R.id.incoming_call_drop_btn:
                mSoundPool.stop(musicId);
                this.finish();
                break;
        }

    }
}
