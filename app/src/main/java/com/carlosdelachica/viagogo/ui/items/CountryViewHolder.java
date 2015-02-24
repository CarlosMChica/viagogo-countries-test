package com.carlosdelachica.viagogo.ui.items;

import android.content.Context;
import android.support.v7.graphics.Palette;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.carlosdelachica.easyrecycleradapters.adapter.EasyViewHolder;
import com.carlosdelachica.viagogo.R;
import com.carlosdelachica.viagogo.domain.entities.countries.Country;
import com.carlosdelachica.viagogo.presentation.countries_translator.CountriesTranslator;
import com.carlosdelachica.viagogo.presentation.countries_translator.CountriesTranslatorImp;
import com.carlosdelachica.viagogo.ui.imageloader.ImageLoader;
import com.carlosdelachica.viagogo.ui.imageloader.PaletteCallback;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class CountryViewHolder extends EasyViewHolder<Country> implements PaletteCallback {

    @InjectView(R.id.imageView)
    ImageView imageView;
    @InjectView(R.id.countryNameTextView)
    TextView countryNameTextView;
    @InjectView(R.id.countryPopulationTextView)
    TextView countryPopulationTextView;
    @InjectView(R.id.countryRegionTextView)
    TextView countryRegionTextView;
    @InjectView(R.id.infoContainer)
    LinearLayout infoContainer;

    private final Context context;
    private final ImageLoader imageLoader;
    private final CountriesTranslator countriesTranslator;

    public CountryViewHolder(Context context, ViewGroup parent, ImageLoader imageLoader) {
        super(context, parent, R.layout.country_item);
        this.context = context;
        this.imageLoader = imageLoader;
        this.countriesTranslator = new CountriesTranslatorImp();
        ButterKnife.inject(this, itemView);
    }

    @Override
    public void bindTo(Country country) {
        bindCountryName(countriesTranslator.translate(country));
        bindCountryFlag(country.getFlagUrl());
        bindCountryPopulation(country.getPopulation());
        bindRegion(country.getRegion());
    }

    private void bindCountryName(String name) {
        countryNameTextView.setText(name);
    }

    private void bindCountryFlag(String flagUrl) {
        imageLoader.loadPalette(flagUrl, imageView, this);
    }

    private void bindCountryPopulation(Integer population) {
        countryPopulationTextView.setText(context.getString(R.string.country_population, population));
    }

    private void bindRegion(String region) {
        countryRegionTextView.setText(context.getString(R.string.country_region, region));
    }

    @Override
    public void onPaletteLoaded(Palette palette) {
        int color = palette.getDarkVibrantColor(android.R.color.black);
        infoContainer.setBackgroundColor(color);
    }

}
