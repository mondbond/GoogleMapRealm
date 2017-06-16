package com.example.mond.googlemaprealm.di;

import com.example.mond.googlemaprealm.App;
import com.example.mond.googlemaprealm.DbHelper;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module
public class MainModule {

    @Provides
    DbHelper providesDbModel() {
        return new DbHelper(App.getAppComponent().getContext());
    }
}
