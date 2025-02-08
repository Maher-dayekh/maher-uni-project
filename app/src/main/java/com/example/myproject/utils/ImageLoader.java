package com.example.myproject.utils;

import android.content.Context;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.example.myproject.R;

public class ImageLoader {
    public static void loadImage(Context context, ImageView imageView, String imageUrl) {
        Glide.with(context)
                .load(imageUrl)
                .placeholder(R.drawable.bg) // ✅ Shows default image while loading
                .error(R.drawable.bg) // ✅ Shows default image if error occurs
                .into(imageView);
    }
}
