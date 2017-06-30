package com.example.mond.googlemaprealm.screens.map.presenter;

import com.example.mond.googlemaprealm.common.BasePresenter;
import com.example.mond.googlemaprealm.model.Marker;
import com.google.android.gms.maps.model.LatLng;

import java.util.List;

public interface MapPresenter extends BasePresenter<com.example.mond.googlemaprealm.screens.map.view.MapView>{

    void addNewMarker(Marker marker);
    void generateMarkers(int count, int radius, LatLng currentLatLng);
    void setUpAllMarkers();
    void setMarkers(List<Marker> markers);
    void showMarkerDetailInfo(com.google.android.gms.maps.model.Marker marker);
    void showLoadingAnimation();
    void hideLoadingAnimation();
}
