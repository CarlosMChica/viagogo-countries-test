package com.carlosdelachica.viagogo.ui.helper_util;

import android.content.Context;
import android.graphics.ColorFilter;
import android.graphics.LightingColorFilter;
import android.graphics.drawable.Drawable;
import android.view.View;

public class HelperUtilImplBase implements HelperUtil {

    protected Context context;

    public HelperUtilImplBase(Context context){
        this.context = context;
    }

    @Override
    public void setElevation(View view, float elevation) {
        // Do nothing pre-L
    }

    @Override
    public void setElevation(View view) {
        // Do nothing pre-L
    }
    @Override
    public void setImmersiveMode(View decorView) {
        // Do nothing pre-KitKat
    }

    @Override
    public void hideNavigationBar(View decorView) {
        // Do nothing pre-KitKat
    }

    @SuppressWarnings("deprecation")
    @Override
    public void setBackgroundDrawable(View view, Drawable drawable) {
        view.setBackgroundDrawable(drawable);
    }

    @Override
    public Drawable getColoredDrawable(Drawable drawable, int targetColor) {
        ColorFilter filter = new LightingColorFilter(targetColor, 0);
        drawable.mutate().setColorFilter(filter);
        return drawable;
    }

}
