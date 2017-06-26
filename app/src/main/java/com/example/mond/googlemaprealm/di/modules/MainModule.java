package com.example.mond.googlemaprealm.di.modules;

import com.example.mond.googlemaprealm.model.MarkerDao;
import com.example.mond.googlemaprealm.screens.detail_marker.presenter.DetailMarkerPresenter;
import com.example.mond.googlemaprealm.screens.detail_marker.presenter.DetailMarkerPresenterImpl;
import com.example.mond.googlemaprealm.screens.map.presenter.MapPresenter;
import com.example.mond.googlemaprealm.screens.map.presenter.MapPresenterImpl;

import dagger.Module;
import dagger.Provides;

@Module
public class MainModule {

    @Provides
    MarkerDao providesDbModel() {
        return new MarkerDao();
    }

    @Provides
    MapPresenter providesMapPresenter() {
        return new MapPresenterImpl(providesDbModel());
    }

    @Provides
    DetailMarkerPresenter providesDetailMarkerPresenter() {
        return new DetailMarkerPresenterImpl(providesDbModel());
    }
}
