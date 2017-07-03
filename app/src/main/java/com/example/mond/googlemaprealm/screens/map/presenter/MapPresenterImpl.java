package com.example.mond.googlemaprealm.screens.map.presenter;

import com.example.mond.googlemaprealm.model.MarkerRepository;
import com.example.mond.googlemaprealm.data.AsyncGeneratorTask;
import com.example.mond.googlemaprealm.model.listeners.ListFindListener;
import com.example.mond.googlemaprealm.model.Marker;
import com.example.mond.googlemaprealm.model.specifications.GetAllResultsSpecification;
import com.example.mond.googlemaprealm.screens.map.view.MapView;
import com.google.android.gms.maps.model.LatLng;

import java.util.List;

public class MapPresenterImpl implements MapPresenter, ListFindListener<Marker>,
        AsyncGeneratorTask.OnGeneratedMarkersSaved {

    private MapView mView;
    private MarkerRepository MarkerRepository;

    public MapPresenterImpl(MarkerRepository repository) {
        MarkerRepository = repository;
    }

    @Override
    public void attachView(MapView view) {
        mView = view;
    }

    @Override
    public void detachView() {
        mView = null;
    }

    @Override
    public void addNewMarker(Marker marker) {
        MarkerRepository.insert(marker);
        MarkerRepository.queryList(new GetAllResultsSpecification(), this);
    }

    @Override
    public void generateMarkers(int count, int radius, LatLng currentLatLng) {
        AsyncGeneratorTask task = new AsyncGeneratorTask(currentLatLng, radius, count, this);
        task.execute();
    }

    @Override
    public void setUpAllMarkers() {
        MarkerRepository.queryList(new GetAllResultsSpecification(), this);
    }

    @Override
    public void setMarkers(List<Marker> markers) {
        if (mView != null) {
            mView.setAllMarkers(markers);
        }
    }

    @Override
    public void showMarkerDetailInfo(com.google.android.gms.maps.model.Marker marker) {
        mView.startDetailActivity(marker);
    }

    @Override
    public void showLoadingAnimation() {
        mView.showLoadingDialog();
    }

    @Override
    public void hideLoadingAnimation() {
        mView.dismissLoadingDialog();
    }

    @Override
    public void onMarkerListCreated(List<Marker> markers) {
        MarkerRepository.insert(markers);
        MarkerRepository.queryList(new GetAllResultsSpecification(), this);
    }

    @Override
    public void onListFind(List<Marker> markers) {
        setMarkers(markers);
    }
}
