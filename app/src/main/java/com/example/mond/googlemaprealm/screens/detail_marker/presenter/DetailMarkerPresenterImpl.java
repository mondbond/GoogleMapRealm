package com.example.mond.googlemaprealm.screens.detail_marker.presenter;

import com.example.mond.googlemaprealm.model.MarkerRepository;
import com.example.mond.googlemaprealm.model.Marker;
import com.example.mond.googlemaprealm.model.listeners.SingleObjectFindListener;
import com.example.mond.googlemaprealm.model.OnLoadSuccessListener;
import com.example.mond.googlemaprealm.model.specifications.GetByIdResultsSpecification;
import com.example.mond.googlemaprealm.screens.detail_marker.view.DetailView;

public class DetailMarkerPresenterImpl implements DetailMarkerPresenter {
    private DetailView mView;
    private MarkerRepository MarkerRepository;

    public DetailMarkerPresenterImpl(MarkerRepository repository) {
        MarkerRepository = repository;
    }

    @Override
    public void attachView(DetailView view) {
        mView = view;
    }

    @Override
    public void detachView() {
        mView = null;
    }

    public void getMarkerById(String id) {
        MarkerRepository.query(new GetByIdResultsSpecification(id), new SingleObjectFindListener<Marker>() {
            @Override
            public void onObjectFind(Marker marker) {
                mView.setMarkerInfo(marker);
            }
        });
    }

    public void updateMarker(Marker marker) {
        MarkerRepository.update(marker, new OnLoadSuccessListener(){
            @Override
            public void onSuccess() {
                mView.transactionFinishedSuccess();
            }
        });
    }

    public void deleteMarker(String id) {
        MarkerRepository.delete(id, new OnLoadSuccessListener() {
            @Override
            public void onSuccess() {
                mView.transactionFinishedSuccess();
            }
        });
    }
}
