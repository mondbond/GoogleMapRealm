package com.example.mond.googlemaprealm.di.modules;

import com.example.mond.googlemaprealm.model.MarkerRepository;
import com.example.mond.googlemaprealm.screens.detail_marker.presenter.DetailMarkerPresenter;
import com.example.mond.googlemaprealm.screens.detail_marker.presenter.DetailMarkerPresenterImpl;
import com.example.mond.googlemaprealm.screens.map.presenter.MapPresenter;
import com.example.mond.googlemaprealm.screens.map.presenter.MapPresenterImpl;

import dagger.Module;
import dagger.Provides;

@Module
public class MainModule {

    @Provides
    MarkerRepository providesDbModel() {
        return new MarkerRepository();
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
