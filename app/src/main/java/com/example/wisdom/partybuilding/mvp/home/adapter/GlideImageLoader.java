package com.example.wisdom.partybuilding.mvp.home.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.wisdom.partybuilding.R;
import com.youth.banner.loader.ImageLoader;

public class GlideImageLoader extends ImageLoader {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {

        try {
            Glide
                    .with(context)
                    .load(path)
                    .placeholder(R.mipmap.lunbo1)
                    .error(R.mipmap.lunbo1)
                    .crossFade(1000)
                    .into(imageView);
        } catch (Exception e) {
        }


    }
}