package com.carlosdelachica.viagogo.ui.helper_util;

import android.graphics.drawable.Drawable;
import android.view.View;

public interface HelperUtil {
    void setElevation(View view, float elevation);
    void setElevation(View view);
    void setImmersiveMode(View decorView);
    void hideNavigationBar(View decorView);
    void setBackgroundDrawable(View view, Drawable drawable);
    Drawable getColoredDrawable(Drawable drawable, int targetColor);
}
