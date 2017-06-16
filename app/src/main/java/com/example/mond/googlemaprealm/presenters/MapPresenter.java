package com.example.mond.googlemaprealm.presenters;

import com.example.mond.googlemaprealm.model.DbMarkerRepository;
import com.example.mond.googlemaprealm.common.BasePresenter;
import com.example.mond.googlemaprealm.model.Marker;
import com.example.mond.googlemaprealm.view.MapView;
import com.google.android.gms.maps.model.LatLng;

import java.util.List;

import javax.inject.Inject;

public class MapPresenter implements BasePresenter<MapView>, DbMarkerRepository.DbMarkerRepositoryListener {

    private MapView mView;
    private DbMarkerRepository mDbMarkerRepository;

    @Inject
    public MapPresenter(DbMarkerRepository helper){
        mDbMarkerRepository = helper;
    }

    @Override
    public void init(MapView view) {
        mView = view;
    }

    public void addNewMarker(String title, int iconType, LatLng latLng){
        mDbMarkerRepository.addNewMarker(title, iconType, latLng);
    }

    public void setUpAllMarkers(){
        mDbMarkerRepository.getAllMarkers(this);
    }

    @Override
    public void setAllMarkers(List<Marker> markers) {
        mView.setAllMarkers(markers);
    }
}
