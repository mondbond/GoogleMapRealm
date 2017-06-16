package com.example.mond.googlemaprealm.di;

import android.content.Context;

import com.example.mond.googlemaprealm.App;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    private App app;

    public AppModule(App app) {
        this.app = app;
    }

    @Provides
    Context providesContext() {
        return app.getApplicationContext();
    }
}
