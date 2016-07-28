package com.smalew.fakecall.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.smalew.fakecall.R;

import butterknife.BindView;

public class TemplateListActivity extends BaseActivity {

    @BindView(R.id.template_list)
    RecyclerView mTemplateList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_template_list);

    }
}
