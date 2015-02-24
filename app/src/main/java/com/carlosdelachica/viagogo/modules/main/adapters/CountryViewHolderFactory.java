package com.carlosdelachica.viagogo.modules.main.adapters;

import android.content.Context;
import android.view.ViewGroup;

import com.carlosdelachica.viagogo.ui.imageloader.ImageLoader;
import com.carlosdelachica.viagogo.ui.items.CountryViewHolder;
import com.carlosdelachica.easyrecycleradapters.adapter.BaseEasyViewHolderFactory;
import com.carlosdelachica.easyrecycleradapters.adapter.EasyViewHolder;

public class CountryViewHolderFactory extends BaseEasyViewHolderFactory {

    private final ImageLoader imageLoader;

    public CountryViewHolderFactory(Context context, ImageLoader imageLoader) {
        super(context);
        this.imageLoader = imageLoader;
    }

    @Override
    public EasyViewHolder create(Class valueClass, ViewGroup parent) {
        return new CountryViewHolder(context, parent, imageLoader);
    }

}

