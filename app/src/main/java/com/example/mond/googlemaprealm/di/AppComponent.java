package com.example.mond.googlemaprealm.di;

import android.content.Context;

import com.example.mond.googlemaprealm.App;

import dagger.Component;

@Component(modules = {AppModule.class})
public interface AppComponent {
    void inject(App app);
    Context getContext();
}