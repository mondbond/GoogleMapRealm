package com.example.mond.googlemaprealm.di;

import com.example.mond.googlemaprealm.view.AddMarkerDialogFragment;
import com.example.mond.googlemaprealm.view.activity.DetailMarkerActivity;
import com.example.mond.googlemaprealm.view.activity.MapsActivity;

import dagger.Component;

@Component(dependencies = {AppComponent.class}, modules = {MainModule.class})
public interface MainComponent {
    void inject(MapsActivity mapsActivity);
    void inject(DetailMarkerActivity detailMarkerActivity);
}
