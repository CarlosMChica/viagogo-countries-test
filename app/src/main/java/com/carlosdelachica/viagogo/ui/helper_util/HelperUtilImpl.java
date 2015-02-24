package com.carlosdelachica.viagogo.ui.helper_util;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.View;

public class HelperUtilImpl implements HelperUtil {

    private final HelperUtil helperUtilInterface;

    public HelperUtilImpl(Context context) {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
            helperUtilInterface =  new HelperUtilImplLollipop(context);
        } else if (Build.VERSION.SDK_INT == Build.VERSION_CODES.KITKAT) {
            helperUtilInterface = new HelperUtilImplKK(context);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            helperUtilInterface = new HelperUtilImplJB(context);
        } else {
            helperUtilInterface = new HelperUtilImplBase(context);
        }
    }

    @Override
    public void setElevation(View view, float elevation){
        helperUtilInterface.setElevation(view, elevation);
    }

    @Override
    public void setElevation(View view){
        helperUtilInterface.setElevation(view);
    }

    @Override
    public void setImmersiveMode(View decorView) {
        helperUtilInterface.setImmersiveMode(decorView);
    }

    @Override
    public void hideNavigationBar(View decorView) {
        helperUtilInterface.hideNavigationBar(decorView);
    }

    @Override
    public void setBackgroundDrawable(View view, Drawable drawable) {
        helperUtilInterface.setBackgroundDrawable(view, drawable);
    }

    @Override
    public Drawable getColoredDrawable(Drawable drawable, int targetColor) {
        return helperUtilInterface.getColoredDrawable(drawable, targetColor);
    }

}
