package com.carlosdelachica.viagogo.modules.detail;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.carlosdelachica.viagogo.R;
import com.carlosdelachica.viagogo.domain.entities.countries.Country;
import com.carlosdelachica.viagogo.presentation.detail.DetailPresenter;
import com.carlosdelachica.viagogo.presentation.detail.DetailView;
import com.carlosdelachica.viagogo.ui.BaseActivity;
import com.carlosdelachica.viagogo.ui.errors.ErrorManager;
import com.carlosdelachica.viagogo.ui.imageloader.ImageLoader;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import butterknife.InjectView;
import butterknife.OnClick;

public class DetailActivity extends BaseActivity implements DetailView {

    public static final String COUNTRY_ALPHA_2_CODE_EXTRA = "CountryAlpha2Code";

    @Inject
    DetailPresenter presenter;
    @Inject
    ImageLoader imageLoader;
    @Inject
    ErrorManager errorManager;

    @InjectView(R.id.activityContainer)
    LinearLayout activityContainer;
    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.capitalTextView)
    TextView capitalTextView;
    @InjectView(R.id.relevanceTextView)
    TextView relevanceTextView;
    @InjectView(R.id.regionTextView)
    TextView regionTextView;
    @InjectView(R.id.populationTextView)
    TextView populationTextView;
    @InjectView(R.id.latLongTextView)
    TextView latLongTextView;
    @InjectView(R.id.demonymTextView)
    TextView demonymTextView;
    @InjectView(R.id.areaTextView)
    TextView areaTextView;
    @InjectView(R.id.giniTextView)
    TextView giniTextView;
    @InjectView(R.id.nativeNameTextView)
    TextView nativeNameTextView;
    @InjectView(R.id.alpha2CodeTextView)
    TextView alpha2CodeTextView;
    @InjectView(R.id.alpha3CodeTextView)
    TextView alpha3CodeTextView;
    @InjectView(R.id.subRegionTextView)
    TextView subRegionTextView;
    @InjectView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;

    private Country country;
    private String alpha2Code;

    private final Target flagImageTarget = new Target() {
        @Override
        public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
            Drawable activityBackground = new BitmapDrawable(getResources(), bitmap);
            helperUtil.setBackgroundDrawable(activityContainer, activityBackground);
        }

        @Override
        public void onBitmapFailed(Drawable errorDrawable) {
        }

        @Override
        public void onPrepareLoad(Drawable placeHolderDrawable) {
        }
    };

    @Override
    public int onCreateViewId() {
        return R.layout.activity_detail;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        parseArguments();
        initUi();
        presenter.uiReady(alpha2Code);
    }

    private void parseArguments() {
        alpha2Code = getIntent().getStringExtra(COUNTRY_ALPHA_2_CODE_EXTRA);
    }

    private void initUi() {
        initToolbar();
        initSwipeRefreshLayout();
    }

    private void initToolbar() {
        if (toolbar == null) {
            return;
        }
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void initSwipeRefreshLayout() {
        swipeRefreshLayout.setEnabled(false);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Override
    public void showGetCountryError() {
        errorManager.showError(getString(R.string.err_getting_countries));
    }

    @Override
    public void refreshUiStarted() {
        swipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void refreshUiFinished() {
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showCountryData(Country country) {
        this.country = country;
        showData();
    }

    private void showData() {
        showToolbarTitle();
        showFlagImage();
        showCapital();
        showRelevance();
        showRegion();
        showPopulation();
        showLatLong();
        showDemonym();
        showArea();
        showGini();
        showNativeName();
        showAlpha2Code();
        showAlpha3Code();
        showSubRegion();
    }

    private void showFlagImage() {
        imageLoader.load(country.getFlagUrl(), flagImageTarget);
    }

    private void showToolbarTitle() {
        setTitle(country.getName());
    }

    private void showCapital() {
        capitalTextView.setText(getString(R.string.capital, country.getCapital()));
    }

    private void showRelevance() {
        relevanceTextView.setText(getString(R.string.relevance, country.getRelevance()));
    }

    private void showRegion() {
        regionTextView.setText(getString(R.string.country_region_click, country.getRegion()));
    }

    private void showAlertDialog(List<String> elements, DialogInterface.OnClickListener onClickListener) {
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        String[] elementsArray = new String[elements.size()];
        for (int i = 0; i < elements.size(); i++) {
            elementsArray[i] = elements.get(i);
        }
        arrayAdapter.addAll(elementsArray);
        new AlertDialog.Builder(this)
                .setAdapter(arrayAdapter, onClickListener)
                .show();
    }

    private void showPopulation() {
        populationTextView.setText(getString(R.string.country_population, country.getPopulation()));
    }

    private void showLatLong() {
        Float lat = country.getLatlng().get(0);
        Float lon = country.getLatlng().get(1);
        latLongTextView.setText(getString(R.string.lat_long, lat, lon));
    }

    private void showDemonym() {
        demonymTextView.setText(getString(R.string.demonym, country.getDemonym()));
    }

    private void showArea() {
        areaTextView.setText(getString(R.string.area, country.getArea()));
    }

    private void showGini() {
        giniTextView.setText(getString(R.string.gini, country.getGini()));
    }

    private void showNativeName() {
        nativeNameTextView.setText(getString(R.string.native_name, country.getNativeName()));
    }

    private void showAlpha2Code() {
        alpha2CodeTextView.setText(getString(R.string.alpha2code, country.getAlpha2Code()));
    }

    private void showAlpha3Code() {
        alpha3CodeTextView.setText(getString(R.string.alpha3code, country.getAlpha3Code()));
    }

    private void showSubRegion() {
        subRegionTextView.setText(getString(R.string.subregion, country.getSubregion()));
    }

    @OnClick(R.id.bordersTextView)
    public void onBordersClick() {
        showAlertDialog(country.getBorders(), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                new DetailActionCommand(DetailActivity.this, country.getBorders().get(which)).execute();
                finish();
            }
        });
    }

    @OnClick(R.id.regionTextView)
    public void onRegionClick() {
        presenter.onRegionClicked(country.getRegion());
    }

    @Override
    public void showCountriesInRegionData(List<Country> countries) {
        String[] countryNames = new String[countries.size()];
        for (int i = 0; i < countries.size(); i++) {
            countryNames[i] = countries.get(i).getName();
        }
        showAlertDialog(Arrays.asList(countryNames), null);
    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.onPause();
    }

    protected List<Object> getModules() {
        return Arrays.<Object>asList(new DetailModule(this));
    }

}

