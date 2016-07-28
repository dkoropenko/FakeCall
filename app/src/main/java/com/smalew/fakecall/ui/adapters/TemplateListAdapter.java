package com.smalew.fakecall.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.smalew.fakecall.R;
import com.smalew.fakecall.data.storage.models.Template;

import java.util.List;

/**
 * Created by koropenkods on 28.07.16.
 */
public class TemplateListAdapter extends RecyclerView.Adapter<TemplateListAdapter.ViewHolder> {

    private List<Template> templates;

    public TemplateListAdapter(List<Template> templates) {
        this.templates = templates;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_template_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Template currentTemplate = templates.get(position);

        holder.mCurrentTemplateName.setText(currentTemplate.getTemplatename());
    }

    @Override
    public int getItemCount() {
        return templates.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private TextView mCurrentTemplateName;
        private ImageView mChangeTemplate;
        private ImageView mPreviewTemplate;

        // TODO: 28.07.16 create listner for ImageView = button

        public ViewHolder(View itemView) {
            super(itemView);

            mCurrentTemplateName = (TextView) itemView.findViewById(R.id.template_content_name);
            mChangeTemplate = (ImageView) itemView.findViewById(R.id.template_change_btn);
            mPreviewTemplate = (ImageView) itemView.findViewById(R.id.template_preview_btn);
        }
    }
}
