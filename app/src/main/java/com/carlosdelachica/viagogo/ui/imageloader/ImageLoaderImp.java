package com.carlosdelachica.viagogo.ui.imageloader;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.graphics.Palette;
import android.widget.ImageView;

import com.carlosdelachica.viagogo.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

public class ImageLoaderImp implements ImageLoader {

    private static final int placeholder = R.drawable.placeholder;

    private Picasso picasso;

    public ImageLoaderImp(Picasso picasso) {
        this.picasso = picasso;
    }

    public void loadPalette(String url, final ImageView imageView, final PaletteCallback paletteCallback) {
        picasso.load(url)
                .placeholder(placeholder)
                .transform(PaletteTransformation.instance())
                .into(imageView, new Callback.EmptyCallback() {
                    @Override
                    public void onSuccess() {
                        Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
                        Palette palette = PaletteTransformation.getPalette(bitmap);
                        paletteCallback.onPaletteLoaded(palette);
                    }
                });
    }

    public void load(String url, Target target) {
        picasso.load(url).placeholder(placeholder).into(target);
    }
        public void load(String url, ImageView imageView) {
        picasso.load(url).placeholder(placeholder).into(imageView);
    }

    @Override public void loadCircular(String url, ImageView imageView) {
        picasso.load(url).transform(new CircleTransformation()).into(imageView);
    }

    @Override
    public void loadPalette(Bitmap bitmap, final PaletteCallback paletteCallback) {
        Palette.generateAsync(bitmap, 24, new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(Palette palette) {
                paletteCallback.onPaletteLoaded(palette);
            }
        });

    }

}
