package com.example.mond.googlemaprealm.di.containers;

import android.content.Context;

import com.example.mond.googlemaprealm.App;
import com.example.mond.googlemaprealm.di.modules.AppModule;

import dagger.Component;

@Component(modules = {AppModule.class})
public interface AppComponent {
    void inject(App app);
    Context getContext();
}
