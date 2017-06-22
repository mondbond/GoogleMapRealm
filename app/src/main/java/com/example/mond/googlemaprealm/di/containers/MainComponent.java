package com.example.mond.googlemaprealm.di.containers;

import com.example.mond.googlemaprealm.App;
import com.example.mond.googlemaprealm.di.modules.MainModule;
import com.example.mond.googlemaprealm.view.activity.DetailMarkerActivity;
import com.example.mond.googlemaprealm.view.activity.MapsActivity;

import dagger.Component;

@Component(modules = {MainModule.class})
public interface MainComponent {
    void inject(App app);
    void inject(MapsActivity mapsActivity);
    void inject(DetailMarkerActivity detailMarkerActivity);
}
