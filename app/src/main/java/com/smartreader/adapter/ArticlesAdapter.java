package com.smartreader.adapter;

import android.app.FragmentManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.smartreader.R;
import com.smartreader.activity.MainActivity;
import com.smartreader.fragment.ArticleDetailFragment;
import com.smartreader.model.Response;
import com.smartreader.model.Result;
import com.smartreader.utils.Constant;
import com.smartreader.utils.FragmentHelper;

/**
 * Created by Nithin Chalakkal on 03-04-2019.
 */

public class ArticlesAdapter extends RecyclerView.Adapter<ArticlesAdapter.ViewHolder> {
    private Response response;

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView txt_Header;
        public TextView date;
        final public ImageView img_article_icon;
        public View layout;
        public TextView source;
        public TextView auther;


        public ViewHolder(View v) {
            super(v);
            layout = v;
            txt_Header = (TextView) v.findViewById(R.id.title);
            img_article_icon = (ImageView)v.findViewById(R.id.img_article_icon);
            date = (TextView)v.findViewById(R.id.date);
            source = (TextView)v.findViewById(R.id.source);
            auther = (TextView)v.findViewById(R.id.byLine);


        }
    }

    public FragmentManager fragmentManager;
    public MainActivity mainActivity;
    // Provide a suitable constructor (depends on the kind of dataset)
    public ArticlesAdapter(Response response, MainActivity mainActivity, FragmentManager fragmentManager) {
        this.response = response;
        this.fragmentManager = fragmentManager;
        this.mainActivity = mainActivity;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ArticlesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                         int viewType) {
        // create a new view
        LayoutInflater inflater = LayoutInflater.from(
                parent.getContext());
        View v =
                inflater.inflate(R.layout.layout_fragment_row, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {


        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ArticleDetailFragment articleDetailFragment = new ArticleDetailFragment();
                Bundle bundle = new Bundle();
                bundle.putString(Constant.BUNDLE_ARTICLE_URL,response.getResults().get(position).getUrl());
                articleDetailFragment.setArguments(bundle);
                FragmentHelper.addAndInitFragmentWithBackStack(articleDetailFragment,R.id.fragment_content_container,fragmentManager);

            }
        });

        final String name = response.getResults().get(position).getTitle();
        holder.txt_Header.setText(name);
        holder.date.setText(response.getResults().get(position).getPublishedDate());

        holder.source.setText(response.getResults().get(position).getSource());
        holder.auther.setText(response.getResults().get(position).getByline()+" ("+response.getResults().get(position).getType()+")");



        Glide.with(mainActivity).load(response.getResults().get(position).getMedia().get(0).getMediaMetadata().get(0).getUrl()).asBitmap().centerCrop().into(new BitmapImageViewTarget(holder.img_article_icon) {
            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable circularBitmapDrawable =
                        RoundedBitmapDrawableFactory.create(mainActivity.getResources(), resource);

                circularBitmapDrawable.setCircular(true);
                holder.img_article_icon.setImageDrawable(circularBitmapDrawable);
            }
        });

    }


    @Override
    public int getItemCount() {
        return response.getResults().size();
    }

}
