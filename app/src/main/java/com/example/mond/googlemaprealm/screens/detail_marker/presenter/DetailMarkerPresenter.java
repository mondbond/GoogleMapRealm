package com.example.mond.googlemaprealm.screens.detail_marker.presenter;

import com.example.mond.googlemaprealm.common.BasePresenter;
import com.example.mond.googlemaprealm.model.Marker;
import com.example.mond.googlemaprealm.screens.detail_marker.view.DetailView;

public interface DetailMarkerPresenter extends BasePresenter<DetailView> {

    void getMarkerById(String id);

    void updateMarker(Marker marker);

    void deleteMarker(String id);
}
