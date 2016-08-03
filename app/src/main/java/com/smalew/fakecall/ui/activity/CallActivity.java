package com.smalew.fakecall.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.smalew.fakecall.R;
import com.smalew.fakecall.data.managers.DataManager;
import com.smalew.fakecall.data.storage.models.Template;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CallActivity extends AppCompatActivity {

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
        initMusic();
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

    private void initPhoto(){
        // TODO: 03.08.16 Create with Picasso
    }

    private void initMusic(){

        // TODO: 03.08.16 Create with SoundPool

    }
}
