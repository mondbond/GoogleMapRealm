package com.example.mond.googlemaprealm.di.modules;

import com.example.mond.googlemaprealm.model.DbMarkerDao;
import com.example.mond.googlemaprealm.presenters.DetailMarkerPresenter;
import com.example.mond.googlemaprealm.presenters.DetailMarkerPresenterImpl;
import com.example.mond.googlemaprealm.presenters.MapPresenter;
import com.example.mond.googlemaprealm.presenters.MapPresenterImpl;

import dagger.Module;
import dagger.Provides;

@Module
public class MainModule {

    @Provides
    DbMarkerDao providesDbModel() {
        return new DbMarkerDao();
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
