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
public class TemplateListAdapter extends RecyclerView.Adapter<TemplateListAdapter.ViewHolder>{

    private List<Template> mTemplates;
    private ViewHolder.CustomClickListener mCustomClickListener;

    public TemplateListAdapter(List<Template> templates, ViewHolder.CustomClickListener listener) {
        this.mTemplates = templates;
        mCustomClickListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_template_list, parent, false);
        return new ViewHolder(view, mCustomClickListener);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Template currentTemplate = mTemplates.get(position);

        holder.mCurrentTemplateName.setText(currentTemplate.getTemplatename());
    }

    @Override
    public int getItemCount() {
        return mTemplates.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView mCurrentTemplateName;
        private ImageView mChangeTemplate;
        private ImageView mPreviewTemplate;

        private CustomClickListener mListener;

        public ViewHolder(View itemView, CustomClickListener listener) {
            super(itemView);

            mListener = listener;

            mCurrentTemplateName = (TextView) itemView.findViewById(R.id.template_content_name);
            mChangeTemplate = (ImageView) itemView.findViewById(R.id.template_change_btn);
            mPreviewTemplate = (ImageView) itemView.findViewById(R.id.template_preview_btn);

            mChangeTemplate.setOnClickListener(this);
            mPreviewTemplate.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mListener != null){
                mListener.onClickOpenTemplate(view, getAdapterPosition());
            }
        }

        public interface CustomClickListener{
            void onClickOpenTemplate(View v, int position);
        }
    }
}
