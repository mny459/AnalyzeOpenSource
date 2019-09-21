package com.mny.learning.superopensource.image;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * @author mny on 2019-05-31.
 * Email：mny9@outlook.com
 * Desc:
 */
public class DemoGlide {
    private void loadBasicImage(Context context, String url, ImageView imageView) {
        Glide.with(context)
                .load(url)
                .into(imageView);
    }

}
