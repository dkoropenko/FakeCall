package com.smalew.fakecall.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.smalew.fakecall.R;
import com.smalew.fakecall.data.managers.DataManager;
import com.smalew.fakecall.data.storage.models.Template;
import com.smalew.fakecall.ui.adapters.TemplateListAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TemplateListActivity extends BaseActivity {
    public static final String TAG = "TemplateListActivity";

    @BindView(R.id.template_list)
    RecyclerView mTemplateList;
    private List<Template> mTemplates;

    private DataManager mDataManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_template_list);
        ButterKnife.bind(this);

        mDataManager = DataManager.getInstance();

        mTemplates = mDataManager.getTemplates();

        // TODO: 28.07.16 Fix code. Create Thread for working with database.

        mTemplateList.setLayoutManager(new LinearLayoutManager(this));
        mTemplateList.setAdapter(new TemplateListAdapter(mTemplates));

    }
}
