package com.example.mond.googlemaprealm.di;

import com.example.mond.googlemaprealm.App;
import com.example.mond.googlemaprealm.model.DbMarkerRepository;

import dagger.Module;
import dagger.Provides;

@Module
public class MainModule {

    @Provides
    DbMarkerRepository providesDbModel() {
        return new DbMarkerRepository(App.getAppComponent().getContext());
    }
}
