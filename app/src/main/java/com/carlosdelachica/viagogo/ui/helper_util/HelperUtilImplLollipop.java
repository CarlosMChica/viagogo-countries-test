package com.carlosdelachica.viagogo.ui.helper_util;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.view.View;

import com.carlosdelachica.viagogo.R;

@TargetApi(Build.VERSION_CODES.LOLLIPOP)
public class HelperUtilImplLollipop extends HelperUtilAboveKitKatImplBase {

    public HelperUtilImplLollipop(Context context){
        super(context);
    }

    @Override
    public void setElevation(View view, float elevation){
        view.setElevation(elevation);
    }

    @Override
    public void setElevation(View view) {
        view.setElevation(view.getResources().getDimensionPixelSize(R.dimen.default_elevation));
    }

}
