package com.example.mond.googlemaprealm.di;

import com.example.mond.googlemaprealm.view.MapsActivity;

import dagger.Component;

@Component(dependencies = {AppComponent.class}, modules = {MainModule.class})
public interface MainComponent {
    void inject(MapsActivity storeDetailActivity);
}
