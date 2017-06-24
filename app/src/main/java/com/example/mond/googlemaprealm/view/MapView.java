package com.example.mond.googlemaprealm.view;

import com.example.mond.googlemaprealm.model.Marker;

import java.util.List;

public interface MapView {
    // TODO: 24/06/17 implement missing methods
//    void showLoadingDialog();
//    void dismissLoadingDialog();
//    void showMarkersLoadingError();
    void setAllMarkers(List<Marker> markers);
}
