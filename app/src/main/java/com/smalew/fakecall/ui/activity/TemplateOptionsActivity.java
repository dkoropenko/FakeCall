package com.smalew.fakecall.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.smalew.fakecall.R;
import com.smalew.fakecall.data.storage.models.Template;
import com.smalew.fakecall.data.storage.models.TemplateDTO;
import com.smalew.fakecall.utils.Constants;

public class TemplateOptionsActivity extends AppCompatActivity {
    
    private TemplateDTO mTemplateDTO;

    // TODO: 29.07.16 continue work. Added BindViews and write in infromation from TemplateDTO

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_template_options);
        
        mTemplateDTO = getIntent().getExtras().getParcelable(Constants.PARCEBLE_VALUE);
        
        
    }
}
