package com.example.mond.googlemaprealm.di.modules;

import com.example.mond.googlemaprealm.model.DbMarkerRepository;

import dagger.Module;
import dagger.Provides;

@Module
public class MainModule {

    @Provides
    DbMarkerRepository providesDbModel() {
        return new DbMarkerRepository();
    }
}
