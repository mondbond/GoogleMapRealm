package com.example.mond.googlemaprealm.di.containers;

import com.example.mond.googlemaprealm.App;
import com.example.mond.googlemaprealm.di.modules.MainModule;
import com.example.mond.googlemaprealm.screens.detail_marker.view.DetailMarkerActivity;
import com.example.mond.googlemaprealm.screens.map.view.MapsActivity;

import dagger.Component;

@Component(modules = {MainModule.class})
public interface MainComponent {
    void inject(App app);
    void inject(MapsActivity mapsActivity);
    void inject(DetailMarkerActivity detailMarkerActivity);
}
