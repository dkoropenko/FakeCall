package com.smalew.fakecall.ui.activity;

import android.annotation.SuppressLint;
import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.smalew.fakecall.R;
import com.smalew.fakecall.data.storage.models.TemplateDTO;
import com.smalew.fakecall.utils.Constants;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TemplateOptionsActivity extends BaseActivity {

    @BindView(R.id.template_edit_name)
    EditText mTemplateName;
    @BindView(R.id.template_edit_describer)
    EditText mDescriberName;
    @BindView(R.id.template_edit_phone_number)
    EditText mPhoneNumber;

    @BindView(R.id.template_text_music)
    TextView mMusicText;
    @BindView(R.id.template_text_avatar)
    TextView mAvatarText;
    @BindView(R.id.template_text_voice)
    TextView mVoiceText;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    
    private TemplateDTO mTemplateDTO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_template_options);



        ButterKnife.bind(this);

        if (savedInstanceState != null){
            mTemplateDTO = savedInstanceState.getParcelable(Constants.PARCABLE_VALUE);
        } else{
            mTemplateDTO = getIntent().getExtras().getParcelable(Constants.PARCABLE_VALUE);
        }

        initToolbar();
        initText();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);

        MenuItem addBtn = menu.findItem(R.id.toolbar_add);
        MenuItem applyBtn = menu.findItem(R.id.toolbar_apply);

        addBtn.setVisible(false);
        applyBtn.setVisible(true);
        return true;
    }

    private void initToolbar(){
        mToolbar.setTitle(R.string.toolbar_change_template);
        setSupportActionBar(mToolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.toolbar_apply){
            showToast(item.getTitle().toString());
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putParcelable(Constants.PARCABLE_VALUE, mTemplateDTO);
    }



    private void initText(){
        mTemplateName.setText(mTemplateDTO.getTemplateName());
        mDescriberName.setText(mTemplateDTO.getSubscribeName());
        mPhoneNumber.setText(mTemplateDTO.getPhoneNumber());

        mMusicText.setText(mTemplateDTO.getMusic());
        mAvatarText.setText(mTemplateDTO.getAvatar());
        mVoiceText.setText(mTemplateDTO.getVoice());
    }
}
