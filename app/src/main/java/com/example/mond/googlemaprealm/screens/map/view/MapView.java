package com.example.mond.googlemaprealm.screens.map.view;

import com.example.mond.googlemaprealm.model.Marker;

import java.util.List;

public interface MapView {
    void showLoadingDialog();
    void dismissLoadingDialog();
    void showMarkersLoadingError();
    void startDetailActivity(com.google.android.gms.maps.model.Marker marker);
    void setAllMarkers(List<Marker> markers);
}
