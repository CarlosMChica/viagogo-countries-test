package com.carlosdelachica.viagogo.modules.main;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.Toolbar;

import com.carlosdelachica.viagogo.R;
import com.carlosdelachica.viagogo.domain.entities.countries.Country;
import com.carlosdelachica.viagogo.presentation.main.MainPresenter;
import com.carlosdelachica.viagogo.presentation.main.MainView;
import com.carlosdelachica.viagogo.ui.BaseActivity;
import com.carlosdelachica.viagogo.ui.errors.ErrorManager;
import com.carlosdelachica.viagogo.ui.imageloader.ImageLoader;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import butterknife.InjectView;

public class MainActivity extends BaseActivity implements MainView,
        SwipeRefreshLayout.OnRefreshListener,
        CountryListFragment.VideosFragmentCallback {

    @Inject
    MainPresenter presenter;
    @Inject
    ErrorManager errorManager;
    @Inject
    ImageLoader imageLoader;

    @InjectView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    @InjectView(R.id.toolbar)
    Toolbar toolbar;

    private CountryListFragment countryListFragment;

    @Override
    public int onCreateViewId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initUi(savedInstanceState);
    }

    private void initUi(Bundle savedInstanceState) {
        initFragment(savedInstanceState);
        initRefreshLayout();
        initToolbar();
    }

    private void initFragment(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            countryListFragment = new CountryListFragment();
        } else {
            countryListFragment = (CountryListFragment) getSupportFragmentManager().findFragmentById(R.id.container);
        }
        countryListFragment.setImageLoader(imageLoader);
        navigateToFragment(countryListFragment);
    }

    private void navigateToFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, fragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .commit();
    }

    private void initRefreshLayout() {
        swipeRefreshLayout.setOnRefreshListener(this);
    }

    private void initToolbar() {
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            helperUtil.setElevation(toolbar);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.onPause();
    }

    @Override
    public void onRefresh() {
        presenter.onRefresh();
    }

    @Override
    public void refreshUiStarted() {
        countryListFragment.onRefresh();
        swipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void refreshUiFinished() {
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void refreshCountriesList(List<Country> countries) {
        countryListFragment.refreshCountries(countries);
    }

    @Override
    public void showGetCountriesError() {
        errorManager.showError(getString(R.string.err_getting_countries));
    }

    @Override
    public void countriesFragmentUiCreated() {
        presenter.uiReady();
    }

    @Override
    public void onRecyclerViewScrolled(boolean firstRawOnTop) {
        swipeRefreshLayout.setEnabled(firstRawOnTop);
    }

    @Override
    protected List<Object> getModules() {
        return Arrays.<Object>asList(new MainModule(this));
    }

}
