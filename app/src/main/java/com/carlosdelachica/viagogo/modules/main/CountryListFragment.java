package com.carlosdelachica.viagogo.modules.main;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.carlosdelachica.easyrecycleradapters.adapter.EasyRecyclerAdapter;
import com.carlosdelachica.easyrecycleradapters.adapter.EasyViewHolder;
import com.carlosdelachica.easyrecycleradapters.recycler_view_manager.EasyRecyclerViewManager;
import com.carlosdelachica.viagogo.R;
import com.carlosdelachica.viagogo.domain.entities.countries.Country;
import com.carlosdelachica.viagogo.modules.detail.DetailActionCommand;
import com.carlosdelachica.viagogo.modules.main.adapters.CountryViewHolderFactory;
import com.carlosdelachica.viagogo.ui.imageloader.ImageLoader;
import com.carlosdelachica.viagogo.ui.items.CountryViewHolder;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class CountryListFragment extends Fragment implements EasyViewHolder.OnItemClickListener {

    @InjectView(R.id.recyclerView)
    RecyclerView recyclerView;
    @InjectView(R.id.empty_list)
    TextView emptyList;

    private EasyRecyclerViewManager recyclerViewManager;
    private VideosFragmentCallback videosFragmentCallback;
    private ImageLoader imageLoader;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        videosFragmentCallback = (VideosFragmentCallback) activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.common_recycler_view, container, false);
        ButterKnife.inject(this, rootView);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        videosFragmentCallback.countriesFragmentUiCreated();
        initUI();
    }

    private void initUI() {
        initRefreshLayout();
        initEasyRecyclerViewManager();
    }

    public void setImageLoader(ImageLoader imageLoader) {
        this.imageLoader = imageLoader;
    }

    private void initEasyRecyclerViewManager() {
        CountryViewHolderFactory countryViewHolderFactory = new CountryViewHolderFactory(getActivity(), imageLoader);
        EasyRecyclerAdapter adapter = new EasyRecyclerAdapter(countryViewHolderFactory);
        adapter.bind(Country.class, CountryViewHolder.class);
        recyclerViewManager = new EasyRecyclerViewManager.Builder(recyclerView, adapter)
                .layoutManager(new GridLayoutManager(getActivity(), getResources().getInteger(R.integer.countries_grid_span_count)))
                .emptyLoadingListTextView(emptyList)
                .loadingListTextColor(android.R.color.white)
                .clickListener(this)
                .build();
    }

    private void initRefreshLayout() {
        recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            }

            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                int topRowVerticalPosition =
                        (recyclerView == null || recyclerView.getChildCount() == 0)
                                ? 0
                                : recyclerView.getChildAt(0).getTop();
                videosFragmentCallback.onRecyclerViewScrolled(topRowVerticalPosition >= 0);
            }
        });
    }

    public void onRefresh(){
        recyclerViewManager.onRefresh();
    }

    public void refreshCountries(List<Country> countries) {
        recyclerViewManager.addAll(countries);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @Override
    public void onItemClick(int position, View view) {
        Country country = (Country) recyclerViewManager.getItem(position);
        new DetailActionCommand(getActivity(), country.getAlpha2Code()).execute();
    }

    public interface VideosFragmentCallback {
        void countriesFragmentUiCreated();
        void onRecyclerViewScrolled(boolean firstRawOnTop);
    }

}
