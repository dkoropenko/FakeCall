package com.smalew.fakecall.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.smalew.fakecall.R;

import java.util.List;

/**
 * Created by koropenkods on 28.07.16.
 */
public class TemplateListAdapter extends RecyclerView.Adapter<TemplateListAdapter.ViewHolder> {

    private List templates;

    public TemplateListAdapter(List templates) {
        this.templates = templates;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // TODO: 28.07.16 Сделать создание элемента меню.
        return null;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // TODO: 28.07.16 Сделать вывод инфы.
    }

    @Override
    public int getItemCount() {
        return templates.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView mChangeTemplate;
        private ImageView mPreviewTemplate;

        public ViewHolder(View itemView) {
            super(itemView);

            mChangeTemplate = (ImageView) itemView.findViewById(R.id.template_change_btn);
            mPreviewTemplate = (ImageView) itemView.findViewById(R.id.template_preview_btn);
        }
    }
}
