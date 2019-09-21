package com.mny.learning.superopensource.base;

import android.app.Activity;
import android.content.Intent;

/**
 * @author mny on 2019-05-23.
 * Email：mny9@outlook.com
 * Desc:
 */
public class ActivityUtils {

    public static final void jump(Activity context, Class<?> activity) {
        context.startActivity(new Intent(context, activity));
    }
}
