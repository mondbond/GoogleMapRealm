package com.example.mond.googlemaprealm.screens.detail_marker.presenter;

import com.example.mond.googlemaprealm.model.MarkerModel;
import com.example.mond.googlemaprealm.model.Marker;
import com.example.mond.googlemaprealm.model.MarkerFindListener;
import com.example.mond.googlemaprealm.model.OnLoadSuccessListener;
import com.example.mond.googlemaprealm.model.specifications.GetByIdResultsSpecification;
import com.example.mond.googlemaprealm.screens.detail_marker.view.DetailView;

public class DetailMarkerPresenterImpl implements DetailMarkerPresenter {
    private DetailView mView;
    private MarkerModel mDbMarkerDao;

    public DetailMarkerPresenterImpl(MarkerModel helper) {
        mDbMarkerDao = helper;
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
        mDbMarkerDao.query(new GetByIdResultsSpecification(id), new MarkerFindListener() {
            @Override
            public void onMarkerFind(Marker marker) {
                mView.setMarkerInfo(marker);
            }
        });
    }

    public void updateMarker(Marker marker) {
        mDbMarkerDao.update(marker, new OnLoadSuccessListener(){
            @Override
            public void onSuccess() {
                mView.transactionFinishedSuccess();
            }
        });
    }

    public void deleteMarker(String id) {
        mDbMarkerDao.delete(id, new OnLoadSuccessListener() {
            @Override
            public void onSuccess() {
                mView.transactionFinishedSuccess();
            }
        });
    }
}
