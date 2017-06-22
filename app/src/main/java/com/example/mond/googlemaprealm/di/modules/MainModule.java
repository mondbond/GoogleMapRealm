package com.example.mond.googlemaprealm.di.modules;

import com.example.mond.googlemaprealm.model.DbMarkerDao;

import dagger.Module;
import dagger.Provides;

@Module
public class MainModule {

    @Provides
    DbMarkerDao providesDbModel() {
        return new DbMarkerDao();
    }
}
