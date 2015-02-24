package com.carlosdelachica.viagogo.ui.imageloader;

import android.graphics.Bitmap;
import android.widget.ImageView;

import com.squareup.picasso.Target;

public interface ImageLoader {
    public void load(String url, Target target);
    void load(String url, ImageView imageView);
    void loadCircular(String url, ImageView imageView);
    void loadPalette(Bitmap bitmap, final PaletteCallback paletteCallback);
    void loadPalette(String url, final ImageView imageView, final PaletteCallback paletteCallback);
}
