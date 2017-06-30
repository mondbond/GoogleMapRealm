package com.example.mond.googlemaprealm.screens.detail_marker.view;


import com.example.mond.googlemaprealm.model.Marker;

public interface DetailView {
    void setMarkerInfo(Marker marker);
    void transactionFinishedSuccess();
}
