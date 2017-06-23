package com.example.mond.googlemaprealm.presenters;

import com.example.mond.googlemaprealm.common.BasePresenter;
import com.example.mond.googlemaprealm.model.Marker;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.model.LatLng;

import java.util.List;

public interface MapPresenter extends BasePresenter<com.example.mond.googlemaprealm.view.MapView>{

    void addNewMarker(Marker marker);

    void generateMarkers(int count, int radius, LatLng currentLatLng);

    void setUpAllMarkers();

    void setMarkers(List<Marker> markers);

}
