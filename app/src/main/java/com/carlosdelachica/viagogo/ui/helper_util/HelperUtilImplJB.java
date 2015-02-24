package com.carlosdelachica.viagogo.ui.helper_util;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.View;

@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
 public class HelperUtilImplJB extends HelperUtilImplBase {

    public HelperUtilImplJB(Context context){
        super(context);
    }

    @Override
    public void setBackgroundDrawable(View view, Drawable drawable) {
        view.setBackground(drawable);
    }

}
