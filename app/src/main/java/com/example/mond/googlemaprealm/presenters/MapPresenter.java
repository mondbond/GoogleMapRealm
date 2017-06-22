package com.example.mond.googlemaprealm.presenters;

import com.google.android.gms.maps.model.LatLng;

public interface MapPresenter {

    void addNewMarker(String title, int iconType, LatLng latLng);

    void generateMarkers(int count, int radius, LatLng currentLatLng);

    void setUpAllMarkers();
}
