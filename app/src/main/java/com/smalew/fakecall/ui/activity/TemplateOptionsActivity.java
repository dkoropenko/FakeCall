package com.smalew.fakecall.ui.activity;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.smalew.fakecall.R;
import com.smalew.fakecall.data.managers.DataManager;
import com.smalew.fakecall.data.storage.models.Template;
import com.smalew.fakecall.data.storage.models.TemplateDTO;
import com.smalew.fakecall.utils.CheckInputInformation;
import com.smalew.fakecall.utils.Constants;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadFactory;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;

public class TemplateOptionsActivity extends BaseActivity {

    private static final String TAG = "TemplateOptionsActivity";

    @BindViews({R.id.template_edit_name_layout,
            R.id.template_edit_describer_layout,
            R.id.template_edit_phone_number_layout})
    List<TextInputLayout> mTextInputLayouts;

    @BindViews({R.id.template_edit_name,
            R.id.template_edit_describer,
            R.id.template_edit_phone_number})
    List<EditText> mTemplatesInfo;

    @BindView(R.id.template_text_music)
    TextView mMusicText;
    @BindView(R.id.template_text_avatar)
    TextView mAvatarText;
    @BindView(R.id.template_text_voice)
    TextView mVoiceText;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    private TemplateDTO mTemplateDTO;
    private Template mTemplate;

    private Boolean activityState;
    private List<CheckInputInformation> watchers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_template_options);

        ButterKnife.bind(this);

        activityState = getIntent().getBooleanExtra(Constants.ADD_NEW_TEMPLATE_FLAG, false);

        if (savedInstanceState != null) {
            mTemplateDTO = savedInstanceState.getParcelable(Constants.PARCABLE_VALUE);
            mTemplate = new Template(mTemplateDTO);
        } else {
            if (activityState) { //when create new template
                mTemplate = new Template();
            } else { //when change exist template
                mTemplateDTO = getIntent().getExtras().getParcelable(Constants.PARCABLE_VALUE);
                mTemplate = new Template(mTemplateDTO);
            }
        }

        initToolbar();
        initText();

        watchers = new ArrayList<>();
        initWatchers();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);

        MenuItem addBtn = menu.findItem(R.id.toolbar_add);
        MenuItem applyBtn = menu.findItem(R.id.toolbar_apply);
        MenuItem deleteBtn = menu.findItem(R.id.toolbar_delete);

        addBtn.setVisible(activityState);
        applyBtn.setVisible(!activityState);
        deleteBtn.setVisible(!activityState);
        return true;
    }

    private void initToolbar() {
        if (activityState) {
            mToolbar.setTitle(R.string.toolbar_create_template);
        } else {
            mToolbar.setTitle(R.string.toolbar_change_template);
        }

        setSupportActionBar(mToolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        final Template template = new Template(
                mTemplatesInfo.get(0).getText().toString(),
                mTemplatesInfo.get(1).getText().toString(),
                mTemplatesInfo.get(2).getText().toString(),
                mMusicText.getText().toString(),
                mAvatarText.getText().toString(),
                mVoiceText.getText().toString()
        );

        Thread thread;

        switch (item.getItemId()) {
            case R.id.toolbar_apply:
                if (!mTextInputLayouts.get(0).isErrorEnabled()) {
                    thread = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            DataManager.getInstance().updateTemplate(mTemplate.getTemplateName(), template);
                        }
                    });
                    thread.run();

                    deleteWathers();
                    Intent intent = new Intent(TemplateOptionsActivity.this, TemplateListActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    finish();
                } else {
                    showToast(getString(R.string.error_check_mistakes));
                }
                break;
            case R.id.toolbar_add:
                if (mTemplatesInfo.get(0).getText().toString().isEmpty()) {
                    mTextInputLayouts.get(0).setErrorEnabled(true);
                    mTextInputLayouts.get(0).setError(getResources().getString(R.string.error_empty_name));
                }

                if (!mTextInputLayouts.get(0).isErrorEnabled()) {
                    thread = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            DataManager.getInstance().setTemplate(template);
                        }
                    });
                    thread.run();

                    deleteWathers();
                    Intent intent = new Intent(TemplateOptionsActivity.this, TemplateListActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    finish();
                } else {
                    showToast(getString(R.string.error_check_mistakes));
                }
                break;
            case R.id.toolbar_delete:
                thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        DataManager.getInstance().deleteTemplate(template);
                    }
                });
                thread.run();

                deleteWathers();
                Intent intent = new Intent(TemplateOptionsActivity.this, TemplateListActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putParcelable(Constants.PARCABLE_VALUE, mTemplateDTO);
    }

    private void initText() {
        mTemplatesInfo.get(0).setText(mTemplate.getTemplateName());
        mTemplatesInfo.get(1).setText(mTemplate.getSubscribeName());
        mTemplatesInfo.get(2).setText(mTemplate.getPhoneNumber());

        mMusicText.setText(mTemplate.getMusic());
        mAvatarText.setText(mTemplate.getAvatar());
        mVoiceText.setText(mTemplate.getVoice());
    }

    private void initWatchers() {

        ArrayList<String> templatesName = getIntent().getStringArrayListExtra(Constants.LIST_VALUES);
        Log.d(TAG, "initWatchers: " + templatesName);

        for (int i = 0; i < mTextInputLayouts.size(); i++) {
            watchers.add(new CheckInputInformation(
                    mTextInputLayouts.get(i),
                    templatesName,
                    mTemplate.getTemplateName())
            );
        }

        for (int i = 0; i < mTemplatesInfo.size(); i++) {
            mTemplatesInfo.get(i).addTextChangedListener(watchers.get(i));
        }
    }

    private void deleteWathers() {
        for (int i = 0; i < mTemplatesInfo.size(); i++) {
            mTemplatesInfo.get(i).removeTextChangedListener(watchers.get(i));
        }
    }
}
