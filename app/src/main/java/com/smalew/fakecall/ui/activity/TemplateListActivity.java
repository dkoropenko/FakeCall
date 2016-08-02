package com.smalew.fakecall.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.redmadrobot.chronos.ChronosConnector;
import com.smalew.fakecall.R;
import com.smalew.fakecall.data.managers.DataManager;
import com.smalew.fakecall.data.storage.models.Template;
import com.smalew.fakecall.data.storage.models.TemplateDTO;
import com.smalew.fakecall.ui.adapters.TemplateListAdapter;
import com.smalew.fakecall.utils.ChronosGetTemplate;
import com.smalew.fakecall.utils.Constants;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TemplateListActivity extends BaseActivity {
    public static final String TAG = "TemplateListActivity";

    @BindView(R.id.template_list)
    RecyclerView mTemplateList;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    final private ChronosConnector mChronosConnector = new ChronosConnector();;

    private DataManager mDataManager;
    private List<Template> mTemplates;
    private ArrayList<String> mTemplateNames;
    private TemplateListAdapter mTemplateListAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_template_list);
        ButterKnife.bind(this);
        mChronosConnector.onCreate(this, savedInstanceState);

        mDataManager = DataManager.getInstance();
        mChronosConnector.runOperation(new ChronosGetTemplate(), false);

        initTollbar();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mChronosConnector.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mChronosConnector.onResume();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mChronosConnector.onSaveInstanceState(outState);
    }

    private void initTollbar(){
        mToolbar.setTitle(R.string.toolbar_templates_list);
        setSupportActionBar(mToolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);

        MenuItem addBtn = menu.findItem(R.id.toolbar_add);
        MenuItem applyBtn = menu.findItem(R.id.toolbar_apply);

        addBtn.setVisible(true);
        applyBtn.setVisible(false);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.toolbar_add){
            Intent intent = new Intent(TemplateListActivity.this, TemplateOptionsActivity.class);
            intent.putExtra(Constants.ADD_NEW_TEMPLATE_FLAG, true);
            intent.putStringArrayListExtra(Constants.LIST_VALUES, mTemplateNames);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    public void onOperationFinished(final ChronosGetTemplate.Result result) {
        if (result.isSuccessful()) {
            showData(result.getOutput());
        } else {
            Log.d(TAG, "onOperationFinished: "+ result.getErrorMessage());
        }
    }

    private void showData(List<Template> result){
        mTemplates = result;

        mTemplateNames = new ArrayList<>();
        for (int i = 0; i < mTemplates.size(); i++) {
            mTemplateNames.add(mTemplates.get(i).getTemplateName());
        }

        mTemplateListAdapter = new TemplateListAdapter(mTemplates, new TemplateListAdapter.ViewHolder.CustomClickListener() {
            @Override
            public void onClickOpenTemplate(View v, int position) {
                TemplateDTO template = new TemplateDTO(mTemplates.get(position));



                switch (v.getId()){
                    case R.id.template_change_btn:
                        Intent intent = new Intent(TemplateListActivity.this, TemplateOptionsActivity.class);
                        intent.putExtra(Constants.PARCABLE_VALUE, template);
                        intent.putStringArrayListExtra(Constants.LIST_VALUES, mTemplateNames);
                        startActivity(intent);
                        break;
                    case R.id.template_preview_btn:
                        showToast("Просмотр");
                        break;
                }
            }
        });

        mTemplateList.setLayoutManager(new LinearLayoutManager(this));
        mTemplateList.setAdapter(mTemplateListAdapter);
    }
}
